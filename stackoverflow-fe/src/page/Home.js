import React, { useState } from 'react';
import styled from 'styled-components';
import AskButton from '../components/list/AskButton';
import RightMenu from '../components/list/RightMenu';
import { Link } from 'react-router-dom';
import Pagination from '../components/pagination/Pagination';
import { useEffect } from 'react';
import useAnsStore from '../store/ansStore';
import axios from 'axios';

const Content = styled.div`
	width: auto;
	display: flex;
	justify-content: center;
	margin-top: 40px;
`;

const Questions = styled.div`
	.Listheader {
		display: flex;
		justify-content: space-between;

		h1 {
			color: #333;
			font-size: 27px;
			font-weight: bold;
			margin-left: 20px;
		}
	}

	.qlist-wrapper {
		display: flex;
		border-top: 1px solid #bebdbd;
		border-bottom: 1px solid #bebdbd;
		padding: 16px;
	}

	.qlist-stats {
		font-size: 13px;
		display: flex;
		align-items: flex-end;
		flex-direction: column;
		width: 105px;
		margin-right: 17px;
	}

	.qlist-stats li {
		margin-bottom: 12px;
		color: #999;
	}

	.qlist-stats .vote {
		color: #333;
	}

	.qlist-stats .number {
		margin-right: 3px;
		font-weight: bold;
	}

	.qlist-contents {
		width: 620px;
	}

	.contents-title {
		font-size: 17px;
		color: #0074cc;
		margin-bottom: 15px;
	}

	.contents-title:hover {
		color: hsl(206deg 100% 52%);
	}

	.tags {
		display: flex;
	}

	.tags li {
		margin-right: 5px;
	}

	.tags span {
		background-color: #e1ecef;
		font-size: 12px;
		color: #39739d;
		padding: 5px;
		border-radius: 3px;
	}

	.tags span:hover {
		background-color: hsl(205deg 53% 88%);
		color: hsl(205deg 46% 32%);
	}

	.user-card {
		display: flex;
		color: #0074cc;
		font-size: 13px;
		align-items: center;
		justify-content: flex-end;
	}

	.user-card li {
		margin-right: 5px;
	}

	.user-card li:last-child {
		margin-right: 0;
	}

	.user-card .user-icon img {
		width: 16px;
		height: 16px;
	}

	.user-card .user-awards {
		color: #333;
		font-weight: bold;
	}
`;

const Contents = () => {
	const { ansList, getAnswer } = useAnsStore();
	const { qTitle, setQtitle } = useState();

	useEffect(() => {
		const questionTitleData = async () => {
			try {
				const response = await axios.get(
					'http://ec2-52-78-27-218.ap-northeast-2.compute.amazonaws.com:8080/questions?page=1&sort=questionId',
				);
				const { data } = response;
				console.log(data.data[0].questionTitle);
				if (data) {
					console.log(data);
				}
			} catch (error) {
				console.error(error);
			}
		};
		questionTitleData();
	}, []);

	useEffect(() => {
		getAnswer(
			`${process.env.REACT_APP_API_URL}/questions?page=1&sort=questionId`,
		);
	}, [getAnswer]);

	const total = ansList.pageInfo && ansList.pageInfo.totalPages;

	return (
		<Content>
			<Questions>
				<div className='Listheader'>
					<h1>Top Questions</h1>
					<AskButton></AskButton>
				</div>
				<div className='qlist-wrapper'>
					<ul className='qlist-stats'>
						<li className='vote'>
							<span className='number'>0</span>
							<span className='unit'>votes</span>
						</li>
						<li className='answer'>
							<span className='number'>0</span>
							<span className='unit'>answers</span>
						</li>
						<li className='view'>
							<span className='number'>0</span>
							<span className='unit'>views</span>
						</li>
					</ul>
					<ul className='qlist-contents'>
						<li className='contents-title'>
							<Link to={'/answer'}>ddd</Link>
						</li>
						<ul className='tags'>
							<li>
								<span>
									<a href='/'>python</a>
								</span>
							</li>
							<li>
								<span>
									<a href='/'>xml</a>
								</span>
							</li>
							<li>
								<span>
									<a href='/'>api</a>
								</span>
							</li>
						</ul>
						<ul className='user-card'>
							<li className='user-icon'>
								<a href='/'>
									<img
										src='https://i.stack.imgur.com/aQbs8.jpg?s=32&g=1'
										alt='profile_img'
									></img>
								</a>
							</li>
							<li className='user-name'>
								<a href='/'>nickname</a>
							</li>
							<li className='user-awards'>2,345</li>
							<li className='time'>
								<a href='/'>asked 2 mins ago</a>
							</li>
						</ul>
					</ul>
				</div>
				<Pagination limit={6} total={total} />
			</Questions>
			<RightMenu></RightMenu>
		</Content>
	);
};

export default Contents;
