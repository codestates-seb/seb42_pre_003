import styled from 'styled-components';
import { useLocation } from 'react-router-dom';

const NavContainer = styled.div`
	width: 164px;
	min-height: calc(100vh - 2506px);
	background-color: #fff;
	padding-top: 30px;
	padding-left: 5px;
	font-size: 13px;
`;

const Navbar = styled.nav`
	width: 100%;
	height: 360px;
	background-color: #fff;
	.tabMenu {
		display: block;
		padding: 10px;
	}
	a {
		display: flex;
		color: #525960;
		padding: 10px;
		&:hover {
			color: #000;
		}
	}
	.active {
		font-weight: bold;
		color: #fff;
		background-color: #000;
		border-radius: 50px;
	}
	.profileActive {
		display: block;
		padding: 12px;
		font-weight: bold;
		color: #fff;
		background-color: #0074cc;
		border-radius: 50px;
	}
`;

const TabMenu = () => {
	const location = useLocation().pathname;

	if (location === '/editprofile') return null;

	return (
		<NavContainer>
			<Navbar>
				<ul>
					<li>
						<a
							href='/mypage/editprofile'
							className={
								location === '/mypage/editprofile' ? 'profileActive' : 'tabMenu'
							}
						>
							Profile
						</a>
					</li>
					<li>
						<a
							href='/mypage/editprofile'
							className={location === '/' ? 'active' : null}
						>
							Answers
						</a>
					</li>
					<li>
						<a
							href='/mypage/editprofile'
							className={location === '/' ? 'active' : null}
						>
							Questions
						</a>
					</li>
					<li>
						<a
							href='/mypage/editprofile'
							className={location === '/' ? 'active' : null}
						>
							Tags
						</a>
					</li>
					<li>
						<a
							href='/mypage/editprofile'
							className={location === '/' ? 'active' : null}
						>
							Bookmark
						</a>
					</li>
				</ul>
			</Navbar>
		</NavContainer>
	);
};

export default TabMenu;
