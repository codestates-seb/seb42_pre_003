import React from 'react';
import styled from 'styled-components';
import AskButton from '../components/list/AskButton';
import RightMenu from '../components/list/RightMenu';
import FilterButton from '../components/list/FilterButton';
import { Link } from 'react-router-dom';

const Content = styled.div`
	width: auto;
	display: flex;
	justify-content: center;
	margin-top: 40px;
`;

const Questions = styled.div`
	.listheader {
		display: flex;
		justify-content: space-between;

		h1 {
			color: #333;
			font-size: 27px;
			font-weight: bold;
			margin-left: 20px;
		}
	}

	.listfilter {
		display: flex;

		.Total {
			font-size: 1.2rem;
			margin-left: 25px;
		}

		.Total span {
			margin-right: 5px;
		}
	}

	.qlist {
		&-wrapper {
			display: flex;
			border-top: 1px solid #bebdbd;
			border-bottom: 1px solid #bebdbd;
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

const Question = () => {
	return (
		<Content>
			<Questions>
				<div className='listheader'>
					<h1>All Questions</h1>
					<AskButton></AskButton>
				</div>
				<div className='listfilter'>
					<div className='Total'>
						<span>1</span>questions
					</div>
					<FilterButton></FilterButton>
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
							<Link to={'/allQuestion/answer'}>
								Why does Prestashop API not add a resource in XML
							</Link>
						</li>
						<li className='contents-excerpt'>
							In our application we have screen with multiple header titles
							(.tasktable__header.tasktable__header--title) and i want to verify
							these titles. The code i had didn't do the job, because it allways
							...
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
			</Questions>
			<RightMenu></RightMenu>
		</Content>
	);
};

export default Question;
