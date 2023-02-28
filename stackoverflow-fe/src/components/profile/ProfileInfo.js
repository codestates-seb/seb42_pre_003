import React from 'react';
import styled from 'styled-components';
import { BirthIcon, TimeIcon, DaysIcon } from './ProfileIcon';

const Container = styled.section`
	width: 700px;
	display: flex;
	align-items: center;
	padding: 30px;

	.profileimg {
		width: 150px;
		height: 150px;
		background-color: #bebdbd;
		border-radius: 3px;
		margin-right: 10px;
	}

	.profileinfo {
		h1 {
			font-size: 37px;
		}

		ul {
			display: flex;
			font-size: 13px;

			li {
				margin-right: 10px;
			}

			svg {
				width: 14px;
				height: 14px;
				margin-right: 3px;
			}
		}
	}
`;

function ProfileInfo() {
	return (
		<Container>
			<div className='profileimg'></div>
			<div className='profileinfo'>
				<h1>UserName</h1>
				<ul>
					<li>
						<BirthIcon />
						Member for <span>1</span> days
					</li>
					<li>
						<TimeIcon />
						Last seen this Week
					</li>
					<li>
						<DaysIcon />
						Visited <span>10</span> days, <span>2</span> consecutive
					</li>
				</ul>
			</div>
		</Container>
	);
}

export default ProfileInfo;
