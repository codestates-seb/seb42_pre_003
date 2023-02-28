import React, { useState } from 'react';
import styled from 'styled-components';
import stacklogo from '../../img/stacklogo.png';
import search from '../../img/search.png';
import { Link } from 'react-router-dom';

import { useIsLoginStore } from '../../store/loginstore';

import { useNavigate, useSearchParams } from 'react-router-dom';

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

const UserInfo = styled.div`
	width: 26px;
	height: 26px;
	border-radius: 50%;
	background-color: #d2d1d1;
`;

const Header = () => {
	const [isLogin, setIsLogin] = useState(false);

	const navigate = useNavigate();

	// let username = '';
	// let id = '';

	// if (JSON.parse(sessionStorage.getItem('userInfoStorage'))) {
	// 	username = JSON.parse(sessionStorage.getItem('userInfoStorage')).email;
	// 	id = JSON.parse(sessionStorage.getItem('userInfoStorage')).memberId;
	// }

	const logoutHandler = () => {
		sessionStorage.clear();
		setIsLogin(false);
		window.location.reload();
	};

	// useEffect(() => {
	// 	if (JSON.parse(window.localStorage.getItem('user'))) {
	// 		setIsLogin(JSON.parse(window.localStorage.getItem('user')).signIn);
	// 	} else {
	// 		setIsLogin(false);
	// 	}
	// }, []);

	// const logoutHandler = () => {
	// 	window.location.replace('/');
	// 	window.localStorage.removeItem('user');
	// };

	return (
		<>
			{isLogin ? (
				<>
					<HeaderWrap>
						<OrangeBg />
						<MenuHeader>
							<LogoImg onClick={() => navigate('/')} src={stacklogo} />

							<SearchWrap>
								<SearchBox placeholder='Search...' />
								<SearchImg src={search} />
							</SearchWrap>
							<UserInfo />
							<LoginLogoutButton onClick={logoutHandler}>
								Log out
							</LoginLogoutButton>
						</MenuHeader>
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
