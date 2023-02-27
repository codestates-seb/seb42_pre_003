import { useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useSearchParams } from 'react-router-dom';

const Oauth = () => {
	const navigate = useNavigate();
	const [searchParams] = useSearchParams();

	useEffect(() => {
		const accessToken = searchParams.get('access_token');
		if (accessToken) {
			sessionStorage.setItem('accesstoken', accessToken);
		}

		navigate('/', { replace: true });
	});

	return <div>구글 플로우</div>;
};

export default Oauth;
