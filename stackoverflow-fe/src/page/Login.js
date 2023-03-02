import styled from 'styled-components';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import logo from '../img/logo.png';
import google from '../img/google.png';
import { useErrorMessageStore, useIsLoginStore } from '../store/loginstore';
import Header from '../components/container/Header';

const Background = styled.div`
	background-color: #f6f6f6;
	width: 100vw;
	height: calc(100vh - 10px);
	display: flex;
	justify-content: center;
	align-items: center;
`;

const Container = styled.div`
	height: 70%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	margin: auto auto;
`;

const Logoimg = styled.img`
	width: 50px;
	padding-bottom: 30px;
`;

const SocialLoginContainer = styled.div`
	background-color: #ffffff;
	display: flex;
	align-items: center;
	box-sizing: border-box;
`;

const GoogleLogin = styled.a`
	width: 320px;
	margin-bottom: 1px;
	height: max-content;
	border: 1px solid #cccccc;
	border-radius: 4px;
	display: flex;
	justify-content: center;
	padding: 3px;
	cursor: pointer;

	&:hover {
		background-color: #f0f0f0;
	}
`;

const SocialLoginIcon = styled.img`
	width: 25px;
	height: 25px;
	margin: 0;
`;

const SocialLoginText = styled.p`
	margin: 0;
	font-size: 15px;
	text-align: center;
	padding-top: 5px;
`;

const LoginFormContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	height: max-content;
	padding-top: 30px;
	padding-bottom: 50px;
`;

const LoginForm = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	border-radius: 8px;
	border: 1px solid #e4e4e4;
	width: 320px;
	height: max-content;
	background-color: white;
	box-shadow: 0 0 5px 5px #e4e4e4;
	padding: 40px 0 40px 0;
`;

const LoginInputContainer = styled.div`
	margin: 0 auto;
	margin-top: 20px;
	width: 80%;
	display: flex;
	flex-direction: column;
`;

const LoginInputInnerContainer = styled.div`
	display: flex;
	align-items: center;
	position: relative;
	width: 100%;
`;

const LoginLabel = styled.div`
	font-weight: 650;
`;

const LoginInput = styled.input`
	width: 100%;
	height: 30px;
	border: 1px solid #babbba;
`;

const LoginButton = styled.button`
	margin-top: 20px;
	width: 80%;
	height: 35px;
	background-color: #289aff;
	color: white;
	border: 1px solid #0078ff;
	border-radius: 5px;
	box-shadow: inset 0 1px 0 0 #6fc0ff;

	&:hover {
		cursor: pointer;
	}
`;

const Text = styled.p`
	font-size: 14px;
	margin-top: 30px;
`;

const Linker = styled.a`
	color: #0a95ff;

	&:hover {
		cursor: pointer;
	}
`;

const Login = () => {
	const navigate = useNavigate();

	const { errorMessage, setErrorMessage } = useErrorMessageStore();
	const { isLogin, setIsLogin } = useIsLoginStore((state) => state);

	const [email, setEmail] = useState();
	const [name, setName] = useState();

	const loginHandler = () => {
		axios.defaults.withCredentials = true;

		const headers = {
			'Access-Control-Allow-Origin': '*',
			'Content-Type': 'application/json',
		};

		if (!name || !email) {
			setName('');
			setEmail('');
			setErrorMessage('Email or password cannot be empty.');
			return;
		} else {
			setErrorMessage('');
		}

		axios
			.post(
				`${process.env.REACT_APP_API_URL}/oauth2/authorization/google`,
				{ name, email },
				{ headers },
			)
			.then((response) => {
				const accessToken = response.headers.get('Authorization').split(' ')[1];
				sessionStorage.setItem('accesstoken', accessToken);
				sessionStorage.setItem(
					'userInfoStorage',
					JSON.stringify(response.data.data),
				);
				setIsLogin(true);
				navigate('/');
			})
			.catch((err) => {
				if (err.response.status === 401) {
					setErrorMessage('The email or password is incorrect.');
					setName('');
					setEmail('');
				}
			});
	};

	return (
		<>
			<Header />
			<Background>
				<Container>
					<Logoimg
						src={logo}
						css={`
							margin-bottom: 30px;
						`}
						alt='logo'
					/>
					<SocialLoginContainer>
						<GoogleLogin
							href={`${process.env.REACT_APP_API_URL}/oauth2/authorization/google`}
						>
							<SocialLoginIcon src={google} />
							<SocialLoginText>Login with Google</SocialLoginText>
						</GoogleLogin>
					</SocialLoginContainer>
					<LoginFormContainer>
						<LoginForm>
							<LoginInputContainer>
								<LoginLabel>Email</LoginLabel>
								<LoginInputInnerContainer>
									<LoginInput />
								</LoginInputInnerContainer>
							</LoginInputContainer>
							<LoginInputContainer>
								<LoginLabel>Password</LoginLabel>
								<LoginInputInnerContainer>
									<LoginInput />
								</LoginInputInnerContainer>
							</LoginInputContainer>
							<LoginButton onClick={loginHandler}>Log in</LoginButton>
						</LoginForm>
						<Text>
							Don’t have an account?
							<Linker
								onClick={() => {
									navigate('/signup');
								}}
							>
								Sign up
							</Linker>
						</Text>
					</LoginFormContainer>
				</Container>
			</Background>
		</>
	);
};

export default Login;
