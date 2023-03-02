import React from 'react';
import styled from 'styled-components';
import stacklogo from '../../img/stacklogo.png';
import search from '../../img/search.png';
import { Link } from 'react-router-dom';
import { useIsLoginStore } from '../../store/loginstore';
import profile from '../../img/profile.png';

import { useNavigate } from 'react-router-dom';

const HeaderWrap = styled.div`
	top: 0;
	position: fixed;
	left: 0;
	width: 100vw;
	height: 53px;
	box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2);
	background-color: #f8f9f9;
	z-index: 1;
`;

const OrangeBg = styled.div`
	width: 100%;
	height: 3px;
	background-color: #32f1ff;
`;
const LoginHeader = styled.div`
	width: 1264px;
	height: 50px;
	display: flex;
	align-items: center;
	justify-content: space-around;
	margin-left: 70px;
	margin-right: auto;
`;

const MenuHeader = styled.div`
	width: 1264px;
	height: 50px;
	display: flex;
	align-items: center;
	justify-content: space-around;
	margin-left: auto;
	margin-right: auto;
`;

const LogoImg = styled.img`
	width: 150px;
`;

const LoginsearchWrap = styled.div`
	position: relative;
	width: 656px;
	height: 32.5px;
	margin-right: -30px;
	margin-left: -50px;
`;

const SearchWrap = styled.div`
	position: relative;
	width: 656px;
	height: 32.5px;
`;

const SearchBox = styled.input`
	width: 656px;
	height: 32.5px;
	padding-left: 30px;
`;

const SearchImg = styled.img`
	position: absolute;
	width: 24px;
	left: 5px;
	top: 6px;
`;

const LogoutButton = styled.a`
	width: 59.5px;
	height: 33px;
	color: #39739d;
	background-color: #e1ecf4;
	font-size: 13px;
	border: 1px solid rgb(122, 167, 199);
	border-radius: 3px;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	margin-right: 70px;
	margin-left: -75px;
`;

const LoginLogoutButton = styled.a`
	width: 59.5px;
	height: 33px;
	color: #39739d;
	background-color: #e1ecf4;
	font-size: 13px;
	border: 1px solid rgb(122, 167, 199);
	border-radius: 3px;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
`;

const SignUpButton = styled.a`
	width: 68px;
	height: 33px;
	color: white;
	background-color: rgb(10, 149, 255);
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 3px;
	border: 1px solid rgb(255, 255, 255, 0);
	font-size: 13px;
	position: relative;
	right: 75px;
`;

const UserInfo = styled.img`
	width: 35px;
	height: 35px;
	margin-right: -15px;
	margin-left: 55px;
	cursor: pointer;
`;

// const ProfileButtonAria = styled.div`
// 	align-items: center;
// 	display: flex;
// 	height: 47px;
// 	justify-content: center;
// 	width: 47px;
// 	> button {
// 		cursor: pointer;
// 	}
// 	&:hover {
// 		background-color: rgb(228, 230, 232);
// 	}
// `;

const Header = () => {
	const navigate = useNavigate();
	const { isLogin, setIsLogin } = useIsLoginStore((state) => state);

	// useEffect(() => {
	// 	if (JSON.parse(sessionStorage.getItem('userInfoStorage'))) {
	// 		setIsLogin(JSON.parse(sessionStorage.getItem('userInfoStorage')).signIn);
	// 	} else {
	// 		setIsLogin(false);
	// 	}
	// }, []);

	// let members = '';
	// let name = '';

	// if (JSON.parse(sessionStorage.getItem('userInfoStorage'))) {
	// 	members = JSON.parse(sessionStorage.getItem('userInfoStorage')).members;
	// 	name = JSON.parse(sessionStorage.getItem('userInfoStorage')).name;
	// }

	// useEffect(() => {
	// 	if (JSON.parse(sessionStorage.getItem('/members/me'))) {
	// 		setIsLogin(JSON.parse(sessionStorage.getItem('/members/me')).value);
	// 	} else {
	// 		setIsLogin(false);
	// 	}
	// }, []);

	// useEffect(() => {
	// 	axios
	// 		.post(
	// 			`${process.env.REACT_APP_API_URL}/members/me`,
	// 			{},
	// 			{
	// 				withCredentials: true,
	// 			},
	// 		)
	// 		.then((res) => {
	// 			console.log(res);
	// 			const { accessToken } = res.data;
	// 			console.log(accessToken);
	// 			axios.defaults.headers.common[
	// 				'Authorization'
	// 			] = `Bearer ${accessToken}`;
	// 			setIsLogin(true);
	// 		});
	// });

	const logoutHandler = () => {
		sessionStorage.clear();
		setIsLogin(false);
		window.location.reload();
	};

	return (
		<>
			{isLogin ? (
				<>
					<HeaderWrap>
						<OrangeBg />
						<LoginHeader>
							<MenuHeader>
								<LogoImg onClick={() => navigate('/')} src={stacklogo} />
								<LoginsearchWrap>
									<SearchBox placeholder='Search...' />
									<SearchImg src={search} />
								</LoginsearchWrap>
								<>
									{/* <ProfileButtonAria>
										<button
											css={`
												all: unset;
												width: 24px;
												height: 24px;
											`}
											onClick={() => {
												navigate(`/myapge/profile`);
											}}
										>
											<img
												src={
													JSON.parse(sessionStorage.getItem('accesstoken'))
														.image
												}
												width='24px'
												height='24px'
												alt='user profile'
											/>
										</button>
									</ProfileButtonAria> */}
									<UserInfo
										onClick={() => navigate('/mypage')}
										src={profile}
									></UserInfo>
									<LogoutButton onClick={logoutHandler}>Log out</LogoutButton>
								</>
							</MenuHeader>
						</LoginHeader>
					</HeaderWrap>
				</>
			) : (
				<>
					<HeaderWrap>
						<OrangeBg />
						<MenuHeader>
							<Link to={'/'}>
								<LogoImg src={stacklogo} />
							</Link>
							<SearchWrap>
								<SearchBox placeholder='Search...' />
								<SearchImg src={search} />
							</SearchWrap>

							<Link to={'/login'}>
								<LoginLogoutButton>Log in</LoginLogoutButton>
							</Link>
							<Link to={'/signup'}>
								<SignUpButton>Sign up</SignUpButton>
							</Link>
						</MenuHeader>
					</HeaderWrap>
				</>
			)}
		</>
	);
};

export default Header;
