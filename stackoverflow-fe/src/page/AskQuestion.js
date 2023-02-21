import styled from 'styled-components';
import AskInput from '../components/ask/AskInput';
import useAskInput from '../util/askUtil/useAskInput';
import AskNotice from '../components/ask/AskNotice';

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

function AskQuestion() {
	const [titleValue, titleBind, titleReset] = useAskInput('');
	const [detailValue, detailBind, detailReset] = useAskInput('');
	const [tryValue, tryBind, tryReset] = useAskInput('');
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

		// localStorage.setItem('ask', JSON.stringify(item));
		console.log(item);

		titleReset();
		detailReset();
		tryReset();
		tagReset();
		reviewReset();
	};

	return (
		<AskWrap onSubmit={handleSubmit}>
			<h3>Ask a public question</h3>
			<AskNotice />
			<AskInput
				label={'Title'}
				text={
					'Be specific and imagine you’re asking a question to another person.'
				}
				values={titleBind}
			/>
			<AskInput
				type={'editor'}
				label={'What are the details of your problem?'}
				text={
					'Introduce the problem and expand on what you put in the title. Minimum 20 characters.'
				}
				values={detailBind}
			/>
			<AskInput
				type={'editor'}
				label={'What did you try and what were you expecting?'}
				text={
					'Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.'
				}
				values={tryBind}
			/>
			<AskInput
				label={'Tags'}
				text={
					'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.'
				}
				values={tagBind}
			/>
			<AskInput
				label={
					'Review questions already on Stack Overflow to see if your question is a duplicate.'
				}
				text={
					'	Clicking on these questions will open them in a new tab for you to review. Your progress here will be saved so you can come back and continue.'
				}
				values={reviewBind}
			/>
			<button>discard</button>
		</AskWrap>
	);
}

export default AskQuestion;
