import styled from 'styled-components';
import { useState } from 'react';
import useAnsStore from '../../store/ansStore';

const PagingBox = styled.div`
	display: flex;
	margin: 2rem 1rem;
	gap: 0.15rem;
`;

const PagingList = styled.ul`
	display: flex;
	gap: 0.15rem;
`;

const PagingLi = styled.li`
	min-width: 1.25rem;
	height: 1.25rem;
	padding: 0 0.25rem;
	color: ${(props) =>
		props.active === 'active' ? '#fff' : 'hsl(210, 8%, 25%)'};
	font-size: 0.7rem;
	font-weight: 500;
	line-height: 1.25rem;
	text-align: center;
	border: 1px solid hsl(210, 8%, 85%);
	border-radius: 0.188rem;
	background: ${(props) => (props.active === 'active' ? '#00ABBB' : '#fff')};
	cursor: pointer;
`;

const PagingButton = styled.button`
	min-width: 1.25rem;
	height: 1.25rem;
	padding: 0 0.25rem;
	color: hsl(210, 8%, 25%);
	font-size: 0.6rem;
	font-weight: 500;
	line-height: 1.25rem;
	text-align: center;
	border: 1px solid hsl(210, 8%, 85%);
	border-radius: 0.188rem;
`;

function Paging({ limit, total = 1 }) {
	const [arr, setArr] = useState(
		Array.from(
			{ length: limit > total ? total : limit },
			(_, index) => index + 1,
		),
	);
	const [curr, setCurr] = useState(1);
	const { ansList, getAnswer } = useAnsStore();

	const handlePaging = (num) => {
		setCurr(num);
		getAnswer(
			`${process.env.REACT_APP_API_URL}/questions?page=${num}&sort=questionId`,
		);
		console.log(ansList);
	};

	const handlePrev = () => {
		if (curr <= arr[0]) {
			let newArr;
			if (arr.length < limit) {
				newArr = Array.from(
					{ length: limit },
					(_, index) => arr[0] - (limit - index),
				);
			} else {
				newArr = arr.map((el) => el - arr.length);
			}
			setArr(newArr);
			handlePaging(newArr[newArr.length - 1]);
		} else {
			handlePaging(curr - 1);
		}
	};

	const handleNext = () => {
		if (curr >= arr[arr.length - 1]) {
			const newArr = arr.map((el) => el + arr.length);
			if (newArr[newArr.length - 1] > total) {
				setArr(newArr.slice(0, newArr.indexOf(total + 1)));
			} else {
				setArr(newArr);
			}
			handlePaging(newArr[0]);
		} else {
			handlePaging(curr + 1);
		}
	};

	return (
		<PagingBox>
			{curr === 1 ? null : (
				<PagingButton onClick={handlePrev}>Prev</PagingButton>
			)}
			<PagingList>
				{arr.map((el, idx) => (
					<PagingLi
						key={idx}
						active={curr === el ? 'active' : null}
						onClick={() => handlePaging(el)}
					>
						{el}
					</PagingLi>
				))}
			</PagingList>
			{arr[arr.length - 1] === total ? null : (
				<PagingButton onClick={handleNext}>Next</PagingButton>
			)}
		</PagingBox>
	);
}

export default Paging;
