import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const ListContents = styled.div`
	width: auto;

	.qlist {
		&-wrapper {
			display: flex;
			border-top: 1px solid #bebdbd;
			padding: 16px;
		}

		&-stats {
			font-size: 13px;
			display: flex;
			align-items: flex-end;
			flex-direction: column;
			width: 105px;
			margin-right: 17px;
		}
	}

	.qlist-stats {
		li {
			margin-bottom: 12px;
			color: #999;
		}
		.vote {
			color: #333;
		}
		.number {
			margin-right: 3px;
			font-weight: bold;
		}
	}

	.qlist-contents {
		width: 620px;
	}

	.contents-title {
		font-size: 17px;
		color: #0074cc;
		margin-bottom: 5px;
	}

	.contents-excerpt {
		font-size: 13px;
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

function AllQuestionList({ listData }) {
	return (
		<ListContents>
			<div className='qlist-wrapper'>
				<ul className='qlist-stats'>
					<li className='vote'>
						<span className='number'>{null ? listData.votes : 0}</span>
						<span className='unit'>votes</span>
					</li>
					<li className='answer'>
						<span className='number'>{null ? listData.answers : 0}</span>
						<span className='unit'>answers</span>
					</li>
					<li className='view'>
						<span className='number'>{null ? 0 : listData.views}</span>
						<span className='unit'>views</span>
					</li>
				</ul>
				<ul className='qlist-contents'>
					<li className='contents-title'>
						<Link to={`/answer/${listData.questionId}`}>
							{listData.questionTitle}
						</Link>
					</li>
					<li className='contents-excerpt'>{listData.questionContent}</li>
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
							<a href='/'>{listData.memberId}</a>
						</li>
						<li className='user-awards'>2,345</li>
						<li className='time'>
							<a href='/'>asked 2 mins ago</a>
						</li>
					</ul>
				</ul>
			</div>
		</ListContents>
	);
}

export default AllQuestionList;
