import { useState } from 'react';
import styled from 'styled-components';
import { useAskInput, useAskEditor } from '../util/askUtil/useAskInput';
import AskReview from '../components/ask/AskReview';
import AskPublic from '../components/ask/AskPublic';

const BREAK_POINT_PC = 1100;

const AskWrap = styled.form`
	max-width: 1264px;
	margin: 0 auto;
	padding: 2rem;
	h3 {
		display: flex;
		height: 6rem;
		align-items: center;
		font-size: 1.25rem;
		font-weight: 800;
		background: url('https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368')
			no-repeat top right/auto 100%;
		@media only screen and (max-width: ${BREAK_POINT_PC}px) {
			background: none;
		}
	}
`;

const BlueButton = styled.button`
	margin-top: 0.4rem;
	margin-right: 1.2rem;
	padding: 0.45rem 0.5rem;
	color: #fff;
	font-size: 0.6rem;
	font-weight: 500;
	border-radius: 0.188rem;
	background: hsl(206, 100%, 52%);
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4);
`;

const DelButton = styled.button`
	margin: 1.2rem 0;
	font-size: 0.65rem;
	font-weight: 500;
	color: #c74d51;
`;

function AskQuestion() {
	const [page, setPage] = useState('ask');

	const data = localStorage.getItem('ask');
	const askData = JSON.parse(data);
	console.log(askData);

	const [titleValue, titleBind, titleReset] = useAskInput(
		askData ? askData.title : '',
	);
	const [detailValue, detailBind, detailReset] = useAskEditor(
		askData ? askData.detail : '',
	);
	const [tryValue, tryBind, tryReset] = useAskEditor(
		askData ? askData.try : '',
	);
	const [tagValue, tagBind, tagReset] = useAskInput(askData ? askData.tag : '');

	const mergeBody = askData && askData.detail + askData.try;
	const [bodyValue, bodyBind, bodyReset] = useAskEditor(
		askData ? mergeBody : '',
	);

	const [status, setStatus] = useState({
		title: true,
		detail: false,
		try: false,
		tag: false,
		review: false,
	});

	if (!localStorage.getItem('able')) {
		localStorage.setItem('able', JSON.stringify(status));
	}

	const handlePage = (page) => {
		setPage(page);
	};

	const handleCashe = (e) => {
		e.preventDefault();

		const item = {
			title: titleValue,
			detail: detailValue,
			try: tryValue,
			tag: tagValue,
		};

		localStorage.setItem('ask', JSON.stringify(item));
		handlePage('review');
	};

	const handleDisable = (item) => {
		let obj = { ...status };
		obj[item] = true;

		setStatus({ ...obj });
		localStorage.setItem('able', JSON.stringify(obj));
	};

	const deleteCashe = (e) => {
		e.preventDefault();
		localStorage.removeItem('ask');
		window.location.reload();
	};

	const handleSubmit = (e) => {
		e.preventDefault();

		//askData 전송(api 부분)
		const item = {
			title: titleValue,
			body: bodyValue,
			tag: tagValue,
		};

		console.log(item);
		localStorage.removeItem('able');
		localStorage.removeItem('ask');

		titleReset();
		detailReset();
		tryReset();
		tagReset();
		bodyReset();

		window.location.reload();
	};

	return (
		<AskWrap onSubmit={handleSubmit}>
			{page === 'review' ? (
				<AskReview
					titleBind={titleBind}
					tagBind={tagBind}
					bodyBind={bodyBind}
				/>
			) : (
				<AskPublic
					titleBind={titleBind}
					detailBind={detailBind}
					tryBind={tryBind}
					tagBind={tagBind}
					handleDisable={handleDisable}
					handleCashe={handleCashe}
				/>
			)}

			{page === 'review' ? <BlueButton>Post your question</BlueButton> : null}
			<DelButton type='button' onClick={deleteCashe}>
				Discard draft
			</DelButton>
		</AskWrap>
	);
}

export default AskQuestion;
