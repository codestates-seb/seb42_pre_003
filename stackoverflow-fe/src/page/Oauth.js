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

	const userInfoStorage = () => {
		const accessToken = sessionStorage.getItem('accesstoken');
		const headers = {
			AUTHORIZATION: `Bearer ${accessToken}`,
		};
		return axios.get(`${process.env.REACT_APP_API_URL}/members/me`, {
			headers,
		});
	};

	return userInfoStorage;
};

export default Oauth;
