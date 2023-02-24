import styled from 'styled-components';
import { useState } from 'react';

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

function Paging() {
	const [arr, setArr] = useState([1, 2, 3, 4, 5, 6]);
	const [curr, setCurr] = useState(1);

	const handlePaging = (num) => {
		setCurr(num);
	};

	const handlePrev = () => {
		if (curr <= arr[0]) {
			const newArr = arr.map((el) => el - arr.length);
			setArr(newArr);
			setCurr(newArr[arr.length - 1]);
		} else {
			setCurr(curr - 1);
		}
	};

	const handleNext = () => {
		if (curr >= arr[arr.length - 1]) {
			const newArr = arr.map((el) => el + arr.length);
			setArr(newArr);
			setCurr(newArr[0]);
		} else {
			setCurr(curr + 1);
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
			<PagingButton onClick={handleNext}>Next</PagingButton>
		</PagingBox>
	);
}

export default Paging;
