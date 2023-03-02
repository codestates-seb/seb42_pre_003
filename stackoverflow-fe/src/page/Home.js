import React, { useState } from 'react';
import styled from 'styled-components';
import AskButton from '../components/list/AskButton';
import RightMenu from '../components/list/RightMenu';
import Pagination from '../components/pagination/Pagination';
import { useEffect } from 'react';
import useAnsStore from '../store/ansStore';
import axios from 'axios';
import QuestionList from '../components/list/QuestionList';

const Content = styled.div`
	width: auto;
	display: flex;
	justify-content: center;
	margin-top: 40px;
`;

const Questions = styled.div`
	.Listheader {
		width: 792px;
		display: flex;
		justify-content: space-between;

		h1 {
			color: #333;
			font-size: 27px;
			font-weight: bold;
			margin-left: 20px;
		}
	}
`;

const Contents = () => {
	const { ansList, getAnswer } = useAnsStore();
	const [listData, setListdata] = useState([]);

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

	console.log(listData);

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
				{listData.data &&
					listData.data.map((el) => <QuestionList listData={el} />)}
				<Pagination limit={6} total={total} />
			</Questions>
			<RightMenu></RightMenu>
		</Content>
	);
};

export default Contents;
