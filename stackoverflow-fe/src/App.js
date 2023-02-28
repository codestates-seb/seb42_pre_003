import GlobalStyle from './style/GlobalStyle';
import Header from './components/container/Header';
import Footer from './components/container/Footer';
import Leftsidebar from './components/container/Leftsidebar';
import Home from './page/Home';
import Login from './page/Login';
import SignUp from './page/SignUp';
import AllQuestion from './page/AllQuestion';
import AskQuestion from './page/AskQuestion';
import Answer from './page/Answer';
import Mypage from './page/Mypage';
import styled from 'styled-components';
import { useEffect } from 'react';
import { useIsLoginStore } from './store/loginstore';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Oauth from './page/Oauth';
import EditProrile from './page/EditProrile';

const Contents = styled.div`
	display: flex;
	margin-top: 50px;
	justify-content: center;
	min-height: 700px;
`;

function App() {
	// const { setIsLogin } = useIsLoginStore((state) => state);

	// useEffect(() => {
	// 	if (sessionStorage.getItem('accesstoken')) {
	// 		return setIsLogin(true);
	// 	}
	// }, []);

	return (
		<>
			<Router>
				<GlobalStyle />
				<div className='App'>
					<Header />
					<Contents>
						<Leftsidebar />
						<Routes>
							<Route exact path='/login' element={<Login />} />
							<Route path='/signUp' element={<SignUp />} />
							<Route path='/' element={<Home />} />
							<Route path='/allQuestion' element={<AllQuestion />} />
							<Route path='/askQuestion' element={<AskQuestion />} />
							<Route path='/allQuestion/answer/:id' element={<Answer />} />
							<Route path='/answer/:id' element={<Answer />} />
							<Route path='/mypage' element={<Mypage />} />
							<Route path='/mypage/editprofile' element={<EditProrile />} />
							<Route path='/auth/google/callback' element={<Oauth />} />
						</Routes>
					</Contents>
					<Footer />
				</div>
			</Router>
		</>
	);
}

export default App;
