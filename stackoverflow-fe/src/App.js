import GlobalStyle from './style/GlobalStyle';
import Header from './components/container/Header';
import Footer from './components/container/Footer';
import Contents from './page/Contents';

function App() {
	return (
		<>
			<GlobalStyle />
			<div className='App'>
				<Header />
				<Contents />
				<Footer />
			</div>
		</>
	);
}

export default App;
