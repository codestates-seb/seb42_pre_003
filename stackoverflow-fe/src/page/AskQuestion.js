import styled from 'styled-components';
import AskInput from '../components/ask/AskInput';
import useAskInput from '../util/askUtil/useAskInput';
import useAskEditor from '../util/askUtil/useAskEditor';
import AskNotice from '../components/ask/AskNotice';
import AskGuide from '../components/ask/AskGuide';

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
	}
`;

const AskBox = styled.div`
	display: flex;
	margin-top: 0.65rem;
	align-items: flex-start;
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

	const handleCashe = (e) => {
		e.preventDefault();
		localStorage.removeItem('ask');
	};

	return (
		<AskWrap onSubmit={handleSubmit}>
			<h3>Ask a public question</h3>
			<AskNotice />
			<AskBox>
				<AskInput
					label={'Title'}
					text={
						'Be specific and imagine you’re asking a question to another person.'
					}
					values={titleBind}
				/>
				<AskGuide
					title={'Writing a good title'}
					description={`Your title should summarize the problem. You might find that you have a better idea of your title after writing out the rest of the question.`}
				/>
			</AskBox>
			<AskBox>
				<AskInput
					type={'editor'}
					label={'What are the details of your problem?'}
					text={
						'Introduce the problem and expand on what you put in the title. Minimum 20 characters.'
					}
					values={detailBind}
				/>
				<AskGuide
					title={'Introduce the problem'}
					description={`Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.`}
				/>
			</AskBox>
			<AskBox>
				<AskInput
					type={'editor'}
					label={'What did you try and what were you expecting?'}
					text={
						'Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.'
					}
					values={tryBind}
				/>
				<AskGuide
					title={'Expand on the problem'}
					description={`Show what you’ve tried, tell us what happened, and why it didn’t meet your needs. Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include a minimal, reproducible example. Please make sure to post code and errors as text directly to the question (and not as images), and format them appropriately.`}
				/>
			</AskBox>
			<AskBox>
				<AskInput
					label={'Tags'}
					text={
						'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.'
					}
					values={tagBind}
				/>
				<AskGuide
					title={'Adding tags'}
					description={`Tags help ensure that your question will get attention from the right people.
					Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.Learn more about tagging`}
				/>
			</AskBox>
			<AskBox>
				<AskInput
					type={'selector'}
					label={
						'Review questions already on Stack Overflow to see if your question is a duplicate.'
					}
					text={
						'	Clicking on these questions will open them in a new tab for you to review. Your progress here will be saved so you can come back and continue.'
					}
					values={reviewBind}
				/>
				<AskGuide
					type={'selector'}
					title={'Make sure we don’t already have an answer for your question'}
					description={`Stack Overflow is a huge database of knowledge.
					Please make sure your question isn’t already answered before posting, or your question might be closed as a duplicate.`}
				/>
			</AskBox>

			<DelButton type='button' onClick={handleCashe}>
				Discard draft
			</DelButton>
		</AskWrap>
	);
}

export default AskQuestion;
