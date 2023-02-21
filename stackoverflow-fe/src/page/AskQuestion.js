import styled from 'styled-components';
import useAskInput from '../util/askUtil/useAskInput';
import useAskEditor from '../util/askUtil/useAskEditor';
import AskNotice from '../components/ask/AskNotice';
import AskBox from '../components/ask/AskBox';

import { useState } from 'react';

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

const DelButton = styled.button`
	margin: 1.2rem 0;
	font-size: 0.65rem;
	font-weight: 500;
	color: #c74d51;
`;

function AskQuestion() {
	const [titleValue, titleBind, titleReset] = useAskInput('');
	const [detailValue, detailBind, detailReset] = useAskEditor('');
	const [tryValue, tryBind, tryReset] = useAskEditor('');
	const [tagValue, tagBind, tagReset] = useAskInput('');
	const [reviewValue, reviewBind, reviewReset] = useAskInput('');

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

	const handleSubmit = (e) => {
		e.preventDefault();

		const item = {
			title: titleValue,
			deatil: detailValue,
			try: tryValue,
			tag: tagValue,
			review: reviewValue,
		};

		localStorage.setItem('ask', JSON.stringify(item));

		titleReset();
		detailReset();
		tryReset();
		tagReset();
		reviewReset();
	};

	const deleteCashe = (e) => {
		e.preventDefault();
		localStorage.removeItem('ask');
	};

	const handleDisable = (item) => {
		let obj = { ...status };
		obj[item] = true;

		setStatus({ ...obj });
		localStorage.setItem('able', JSON.stringify(obj));
	};

	return (
		<AskWrap onSubmit={handleSubmit}>
			<h3>Ask a public question</h3>
			<AskNotice />
			<AskBox
				name={'title'}
				values={titleBind}
				onClick={() => handleDisable('detail')}
				inputLabel={'Title'}
				inputText={
					'Be specific and imagine you’re asking a question to another person.'
				}
				guideTitle={'Writing a good title'}
				guideDes={`Your title should summarize the problem.\n
				You might find that you have a better idea of your title after writing out the rest of the question.`}
			/>
			<AskBox
				name={'detail'}
				type={'editor'}
				values={detailBind}
				onClick={() => handleDisable('try')}
				inputLabel={'What are the details of your problem?'}
				inputText={
					'Introduce the problem and expand on what you put in the title. Minimum 20 characters.'
				}
				guideTitle={'Introduce the problem'}
				guideDes={`Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.`}
			/>
			<AskBox
				name={'try'}
				type={'editor'}
				values={tryBind}
				onClick={() => handleDisable('tag')}
				inputLabel={'What did you try and what were you expecting?'}
				inputText={
					'Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.'
				}
				guideTitle={'Expand on the problem'}
				guideDes={`Show what you’ve tried, tell us what happened, and why it didn’t meet your needs.\n
				Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include a minimal, reproducible example.\n
				Please make sure to post code and errors as text directly to the question (and not as images), and format them appropriately.`}
			/>
			<AskBox
				name={'tag'}
				values={tagBind}
				onClick={() => handleDisable('review')}
				inputLabel={'Tags'}
				inputText={
					'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.'
				}
				guideTitle={'Adding tags'}
				guideDes={`Tags help ensure that your question will get attention from the right people.\n
				Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.\n
				Learn more about tagging`}
			/>
			<AskBox
				name={'review'}
				type={'selector'}
				values={'review'}
				titleBind={reviewBind}
				inputLabel={
					'Review questions already on Stack Overflow to see if your question is a duplicate.'
				}
				inputText={
					'Clicking on these questions will open them in a new tab for you to review. Your progress here will be saved so you can come back and continue.'
				}
				guideTitle={
					'Make sure we don’t already have an answer for your question'
				}
				guideDes={`Stack Overflow is a huge database of knowledge.\n
				Please make sure your question isn’t already answered before posting, or your question might be closed as a duplicate.`}
			/>

			<DelButton type='button' onClick={deleteCashe}>
				Discard draft
			</DelButton>
		</AskWrap>
	);
}

export default AskQuestion;
