import google from '../img/google.png';
import icon1 from '../img/icon1.png';
import icon2 from '../img/icon2.png';
import icon3 from '../img/icon3.png';
import icon4 from '../img/icon4.png';

import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';

const Container = styled.div`
	width: 100vw;
	height: calc(100vh + 140px);
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #f1f2f3;
`;

const LoginWrap = styled.div`
	width: 775px;
	height: 935px;
	display: flex;
	align-items: center;
`;

const LeftBox = styled.div`
	width: 415px;
	height: 285px;
	margin-bottom: 150px;
`;

const Title = styled.div`
	font-size: 32px;
	margin-bottom: 32px;
`;

const SubTitleWrap = styled.div`
	margin-bottom: 24px;
	display: flex;
	align-items: center;
`;

const Icon = styled.img`
	width: 40px;
`;

const SubTitle = styled.span`
	font-size: 15px;
	color: #313131;
	margin-left: 9px;
`;

const Desc = styled.div`
	font-size: 13px;
	color: #656565;
	margin-top: 30px;
`;

const DescLink = styled.div`
	color: #0074cc;
	margin-top: 5px;
`;

const RightBox = styled.div`
	width: 315px;
	height: 935px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	margin-bottom: 100px;
`;

const GoogleSignupButton = styled.div`
	width: 315px;
	height: 38px;
	border-radius: 5px;
	border: 1px solid rgb(214, 217, 220);
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 20px;
	background-color: white;
	cursor: pointer;

	&:hover {
		background-color: #f0f0f0;
	}
`;

const GoogleImg = styled.img`
	width: 18px;
`;

const GoogleText = styled.div`
	margin-left: 4px;
	margin-top: 2px;
	font-size: 16px;
`;

const SignUpForm = styled.div`
	width: 315px;
	height: 410px;
	background-color: white;
	border-radius: 7px;
	padding: 24px;
	box-shadow: 0 0 3px 3px #e4e4e4;
	display: flex;
	align-items: center;
	flex-direction: column;
`;

const Name = styled.div`
	width: 240px;
	font-weight: 600;
	font-size: 15px;
	margin-bottom: 5px;
`;

const NameForm = styled.input`
	width: 240px;
	height: 33px;
	font-size: 13px;
	padding: 8px 9px;
	border: ${({ isNameError }) =>
		isNameError ? '1px solid #d0393e' : '1px solid rgb(186, 191, 196)'};
	border-radius: 4px;
	font-weight: 400px;
	margin-bottom: 10px;
`;

const Email = styled.div`
	width: 240px;
	font-weight: 600;
	font-size: 15px;
	margin-bottom: 5px;
`;

const EmailForm = styled.input`
	width: 240px;
	height: 33px;
	font-size: 13px;
	padding: 8px 9px;
	border: ${({ isEmailError }) =>
		isEmailError ? '1px solid #d0393e' : '1px solid rgb(186, 191, 196)'};
	border-radius: 4px;
	font-weight: 400px;
	margin-bottom: 10px;
`;

const Password = styled.div`
	width: 240px;
	font-weight: 600;
	font-size: 15px;
	margin-bottom: 5px;
`;

const PasswordForm = styled.input`
	width: 240px;
	height: 33px;
	font-size: 13px;
	padding: 7.8px 9.1px;
	border: ${({ isPwError }) =>
		isPwError ? '1px solid #d0393e' : '1px solid rgb(186, 191, 196)'};
	border-radius: 4px;
	font-weight: 400px;
	margin-bottom: 10px;
`;

const PasswordMessage = styled.div`
	width: 240px;
	height: 48px;
	font-size: 14px;
	color: #4b4b4b;
	margin-bottom: 16px;
`;

const SignSubmit = styled.div`
	padding: 10px;
	width: 240px;
	height: 38px;
	background-color: #0a95ff;
	color: white;
	box-shadow: inset 0 1px 0 0 #6fc0ff;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
`;

const LoginMessage = styled.div`
	font-size: 14px;
	color: #393939;
	margin-top: 40px;
	display: flex;
	justify-content: center;
`;

const LoginLink = styled.div`
	color: rgb(0, 116, 204);
	margin-left: 5px;
	cursor: pointer;
`;

const SignupAgreeInfo = styled.div`
	line-height: 1rem;
	text-align: left;
	color: hsl(210, 8%, 45%);
	font-size: 12px;
	margin-top: 32px;
	margin-left: 20px;

	> a {
		color: #0a95ff;
		&:hover {
			cursor: pointer;
		}
	}
`;

const Error = styled.div`
	color: #d0393e;
	font-size: 12px;
	margin-bottom: 13px;
	width: 240px;
`;

const SignUp = () => {
	const [name, setName] = useState('');
	const [isNameError, setIsNameError] = useState(false);
	const [namelErrorMessage, setnameErrorMessage] = useState('');
	const [nameState, setNameState] = useState(false);

	const [email, setEmail] = useState('');
	const [isEmailError, setIsEmailError] = useState(false);
	const [emailErrorMessage, setEmailErrorMessage] = useState('');
	const [emailState, setEmailState] = useState(false);

	const [password, setPassword] = useState('');
	const [isPwError, setIsPwError] = useState(false);
	const [pwErrorMessage, setPwErrorMessage] = useState('');
	const [pwState, setPwState] = useState(false);

	const navigate = useNavigate();

	const nameHandler = (e) => {
		setName(e.target.value);
	};

	const emailHandler = (e) => {
		setEmail(e.target.value);
	};

	const passwordHandler = (e) => {
		setPassword(e.target.value);
	};

	const moveLogin = () => {
		navigate('/login');
	};

	useEffect(() => {
		if (name === '') {
			setIsNameError(false);
		} else if (name.match(/^[0-9A-Za-z가-힣]{2,30}$/)) {
			setIsNameError(false);
			setNameState(true);
		} else {
			setnameErrorMessage('한글 / 영문만 허용됩니다.');
			setIsNameError(true);
			setNameState(false);
		}

		if (email === '') {
			setIsEmailError(false);
		} else if (
			email.match(
				/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,
			)
		) {
			setIsEmailError(false);
			setEmailState(true);
		} else {
			setEmailErrorMessage('Please enter the correct email format');
			setIsEmailError(true);
			setEmailState(false);
		}

		if (password === '') {
			setIsPwError(false);
		} else if (password.length >= 8 && password.match(/[a-zA-Z]+[0-9]+/)) {
			setIsPwError(false);
			setPwState(true);
		} else if (!password.match(/[0-9]+/) && password.match(/[a-zA-z]+/)) {
			setPwErrorMessage(
				'Please add one of the following things to make your password stronger: number',
			);
			setIsPwError(true);
			setPwState(false);
		} else if (password.match(/[0-9]+/) && !password.match(/[a-zA-z]+/)) {
			setPwErrorMessage(
				'Please add one of the following things to make your password stronger: letters',
			);
			setIsPwError(true);
			setPwState(false);
		} else if (password.length < 8) {
			setPwErrorMessage('Please enter at least 8 characters');
			setIsPwError(true);
			setPwState(false);
		}
	}, [name, email, password]);

	// const signupHandler = () => {
	// 	if (emailState === true && nameState === true) {
	// 		axios
	// 			.post(
	// 				'http://ec2-52-78-27-218.ap-northeast-2.compute.amazonaws.com:8080/members',
	// 				{ withCredentials: true },
	// 				{
	// 					name,
	// 					email,
	// 				},
	// 			)
	// 			.then((res) => res.data)
	// 			.then((res) => {
	// 				console.log(res);
	// 				if (res.signup === true) {
	// 					navigate('/login');
	// 				} else {
	// 					console.log('이미 가입된 회원입니다.');
	// 				}
	// 			});
	// 	}
	// };

	const signupHandler = async () => {
		if (email === '' || name === '') {
			console.log('회원가입 실패', '빈 칸이 없어야 합니다.', 'error');
		} else {
			try {
				await axios
					.post(
						`http://ec2-52-78-27-218.ap-northeast-2.compute.amazonaws.com:8080/members`,
						{
							email: email,
							username: name,
						},
					)
					.then(navigate('/login'));
			} catch (error) {
				alert(error);
			}
		}
	};

	return (
		<>
			<Container>
				<LoginWrap>
					<LeftBox>
						<Title>Join the Stack Overflow community</Title>
						<SubTitleWrap>
							<Icon src={icon1} />
							<SubTitle>
								Unlock new privileges like voting and commenting
							</SubTitle>
						</SubTitleWrap>
						<SubTitleWrap>
							<Icon src={icon2} />
							<SubTitle>
								Unlock new privileges like voting and commenting
							</SubTitle>
						</SubTitleWrap>
						<SubTitleWrap>
							<Icon src={icon3} />
							<SubTitle>Save your favorite tags, filters, and jobs</SubTitle>
						</SubTitleWrap>
						<SubTitleWrap>
							<Icon src={icon4} />
							<SubTitle>Earn reputation and badges</SubTitle>
						</SubTitleWrap>
						<Desc>
							Collaborate and share knowledge with a private group for FREE.
							<DescLink>
								Get Stack Overflow for Teams free for up to 50 users
							</DescLink>
						</Desc>
					</LeftBox>
					<RightBox>
						<GoogleSignupButton>
							<GoogleImg src={google} />
							<GoogleText>Sign up with Google</GoogleText>
						</GoogleSignupButton>
						<SignUpForm>
							<Name>Display name</Name>
							<NameForm onChange={nameHandler} isNameError={isNameError} />
							{isNameError ? <Error>{namelErrorMessage}</Error> : null}
							<Email>Email</Email>
							<EmailForm onChange={emailHandler} isEmailError={isEmailError} />
							{isEmailError ? <Error>{emailErrorMessage}</Error> : null}
							<Password>Password</Password>
							<PasswordForm
								type='password'
								onChange={passwordHandler}
								isPwError={isPwError}
							/>
							{isPwError ? <Error>{pwErrorMessage}</Error> : null}
							<PasswordMessage>
								Passwords must contain at least eight characters, including at
								least 1 letter and 1 number.
							</PasswordMessage>
							<SignSubmit className='signup' onClick={signupHandler}>
								Sign up
							</SignSubmit>
							<SignupAgreeInfo>
								By clicking “Sign up”, you agree to our
								<a href='https://stackoverflow.com/legal/terms-of-service/public'>
									terms of service
								</a>
								,
								<a href='https://stackoverflow.com/legal/privacy-policy'>
									privacy policy
								</a>{' '}
								and
								<a href='https://stackoverflow.com/legal/cookie-policy'>
									cookie policy
								</a>
							</SignupAgreeInfo>
						</SignUpForm>
						<LoginMessage>
							Don't have an account?
							<LoginLink onClick={moveLogin}>Log in</LoginLink>
						</LoginMessage>
					</RightBox>
				</LoginWrap>
			</Container>
		</>
	);
};

export default SignUp;
