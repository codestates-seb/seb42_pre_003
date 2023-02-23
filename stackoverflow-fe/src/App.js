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
import styled from 'styled-components';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const Contents = styled.div`
	display: flex;
	margin-top: 50px;
	justify-content: center;
`;

function App() {
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
							<Route path='/answer' element={<Answer />} />
						</Routes>
					</Contents>
					<Footer />
				</div>
			</Router>
		</>
	);
}

export default App;
