import styled from 'styled-components';
import { useLocation } from 'react-router-dom';

const NavContainer = styled.div`
	width: 164px;
	min-height: calc(100vh - 2506px);
	background-color: #fff;
	border-right: 1px solid #c3c3c3;
	padding-top: 30px;
	font-size: 13px;
`;

const Navbar = styled.nav`
	width: 100%;
	height: 360px;
	background-color: #fff;
	.homeMenu {
		display: block;
		padding: 8px 6px 8px 6px;
	}
	a {
		display: flex;
		color: #525960;
		padding: 8px 6px 8px 6px;
		&:hover {
			color: #000;
		}
	}
	.active {
		font-weight: bold;
		color: #000;
		background-color: #f1f2f3;
		border-right: 3px solid #00abbb;
	}
	.homeActive {
		display: block;
		padding: 8px 6px 8px 6px;
		font-weight: bold;
		color: #000;
		background-color: #f1f2f3;
		border-right: 3px solid #00abbb;
	}
`;

const Leftsidebar = () => {
	const location = useLocation().pathname;

	if (
		location === '/login' ||
		location === '/signup' ||
		location === '/askquestion'
	)
		return null;

	return (
		<NavContainer>
			<Navbar>
				<ul>
					<li>
						<a
							href='/'
							className={
								location === '/' ||
								(location.includes('/answer/') &&
									!location.includes('/allQuestion/'))
									? 'homeActive'
									: 'homeMenu'
							}
						>
							Home
						</a>
					</li>
					<li>
						<a
							href='/allQuestion'
							className={
								location === '/allQuestion' ||
								location.includes('/allQuestion/answer/')
									? 'active'
									: null
							}
						>
							Questions
						</a>
					</li>
					<li>
						<a href='/tags' className={location === '/tags' ? 'active' : null}>
							Tags
						</a>
					</li>
					<li>
						<a
							href='/users'
							className={location === '/users' ? 'active' : null}
						>
							Users
						</a>
					</li>
				</ul>
			</Navbar>
		</NavContainer>
	);
};

export default Leftsidebar;
