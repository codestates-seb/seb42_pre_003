import styled from 'styled-components';
import { useState } from 'react';

const PagingBox = styled.div`
	display: flex;
	margin: 2rem 0;
	gap: 0.15rem;
`;

const PagingList = styled.ul`
	display: flex;
	gap: 0.15rem;
	li {
		min-width: 1.25rem;
		height: 1.25rem;
		padding: 0 0.25rem;
		color: hsl(210, 8%, 25%);
		font-size: 0.7rem;
		font-weight: 500;
		line-height: 1.25rem;
		text-align: center;
		border: 1px solid hsl(210, 8%, 85%);
		border-radius: 0.188rem;
		/* background: ${(props) =>
			props.active === 'active' ? '#00ABBB' : '#fff'}; */
	}
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
		setCurr(curr - 1);
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

	// console.log(curr);

	return (
		<PagingBox>
			{curr === 1 ? null : (
				<PagingButton onClick={handlePrev}>Prev</PagingButton>
			)}
			<PagingList>
				{arr.map((el, idx) => (
					<li key={idx} onClick={() => handlePaging(idx + 1)}>
						{el}
					</li>
				))}
			</PagingList>
			<PagingButton onClick={handleNext}>Next</PagingButton>
		</PagingBox>
	);
}

export default Paging;
