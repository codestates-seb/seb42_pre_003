import React from 'react';
import styled from 'styled-components';

const Taglist = styled.section`
	width: auto;
	margin-left: 30px;

	.title {
		font-size: 19px;
		color: #333;
		font-weight: bold;
	}

	ul {
		font-size: 11px;
		color: #333;
	}

	ul span {
		background-color: #e1ecef;
		font-size: 12px;
		color: #39739d;
		padding: 6px;
		border-radius: 3px;
	}

	ul li {
		margin-top: 25px;
	}

	ul span:hover {
		background-color: hsl(205deg 53% 88%);
		color: hsl(205deg 46% 32%);
	}
`;

const RightMenu = () => {
	return (
		<Taglist>
			<div className='title'>Related Tags</div>
			<ul>
				<li>
					<span>
						<a href='/'>javascript</a>
					</span>{' '}
					x 2477493
				</li>
				<li>
					<span>
						<a href='/'>python</a>
					</span>{' '}
					x 2110198
				</li>
				<li>
					<span>
						<a href='/'>java</a>
					</span>{' '}
					x 1889488
				</li>
				<li>
					<span>
						<a href='/'>c#</a>
					</span>{' '}
					x 1582806
				</li>
				<li>
					<span>
						<a href='/'>php</a>
					</span>{' '}
					x 1456747
				</li>
				<li>
					<span>
						<a href='/'>android</a>
					</span>{' '}
					x 1400154
				</li>
				<li>
					<span>
						<a href='/'>html</a>
					</span>{' '}
					x 1167353
				</li>
				<li>
					<span>
						<a href='/'>jquery</a>
					</span>{' '}
					x 1036629
				</li>
				<li>
					<span>
						<a href='/'>c++</a>
					</span>{' '}
					x 789149
				</li>
				<li>
					<span>
						<a href='/'>css</a>
					</span>{' '}
					x 787244
				</li>
			</ul>
		</Taglist>
	);
};

export default RightMenu;
