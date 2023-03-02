import React, { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import AskButton from '../components/list/AskButton';
import RightMenu from '../components/list/RightMenu';
import Filter from '../components/list/Filter';
import Filtericon from '../img/filter_ic.svg';
import AllQuestionList from '../components/list/AllQuestionList';
import axios from 'axios';

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
		justify-content: space-between;

		.Total {
			font-size: 1.2rem;
			margin-left: 25px;
		}

		.Total span {
			margin-right: 5px;
		}

		button {
			width: 71px;
			height: 35px;
			color: #39739d;
			background-color: #e1ecf4;
			border-radius: 3px;
			font-size: 13px;
			margin-bottom: 20px;
			border: 1px solid #39739d;
		}

		button:hover {
			color: #2c5877;
			background-color: #b3d3ea;
		}
	}

	.filterinfo {
		margin-bottom: 15px;
		height: 0;
		visibility: hidden;
		transition: opacity 0.2s ease-in-out, height 0.1s 0.1s ease-in-out;
	}

	.animated {
		visibility: visible;
		transition: height 0.1s ease-in-out, opacity 0.2s 0.1s ease-in-out;
	}
`;

const FilterImg = styled.img`
	width: 10px;
	height: 10px;
`;

const Question = () => {
	const [toggle, setToggle] = useState(false);
	const [heightEl, setHeightEl] = useState();
	const [listData, setListdata] = useState({});

	const refHeight = useRef();

	useEffect(() => {
		setHeightEl(`${refHeight.current.scrollHeight}px`);
	}, []);

	const toggleState = () => {
		setToggle(!toggle);
	};

	useEffect(() => {
		const questionData = async () => {
			try {
				const response = await axios.get(
					'http://ec2-52-78-27-218.ap-northeast-2.compute.amazonaws.com:8080/questions?page=1&sort=questionId',
				);
				const { data } = response;
				setListdata(data);
				if (data) {
					console.log(data);
				}
			} catch (error) {
				console.error(error);
			}
		};
		questionData();
	}, []);

	return (
		<Content>
			<Questions>
				<div className='listheader'>
					<h1>All Questions</h1>
					<AskButton></AskButton>
				</div>
				<div className='listfilter'>
					<div className='Total'>
						<span>0</span>
						questions
					</div>
					<button onClick={toggleState}>
						<FilterImg src={Filtericon}></FilterImg>
						Filter
					</button>
				</div>
				<div
					className={toggle ? 'filterinfo animated' : 'filterinfo'}
					style={{ height: toggle ? `${heightEl}` : '0px' }}
					ref={refHeight}
				>
					<Filter></Filter>
				</div>
				{listData.data &&
					listData.data.map((el) => <AllQuestionList listData={el} />)}
			</Questions>
			<RightMenu></RightMenu>
		</Content>
	);
};

export default Question;
