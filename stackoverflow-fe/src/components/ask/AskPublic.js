import AskNotice from './AskNotice';
import AskBox from './AskBox';
import { useAskStore, useBoxStore } from '../../store/askStore';

function AskPublic() {
	const { titleBind, detailBind, expectBind, tagBind } = useAskStore();
	const { ableData, setAbleData, setAskData } = useBoxStore();
	const { title, detail, expect, tag, handlePage } = useAskStore();

	const handleDisable = (item) => {
		let obj = { ...ableData[0] };
		obj[item] = true;
		setAbleData(obj);
	};

	const handleCashe = (e) => {
		e.preventDefault();

		const item = {
			title,
			detail,
			expect,
			tag,
		};

		console.log(item);

		setAskData(item);
		handlePage();
	};

	return (
		<>
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
				values={expectBind}
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
				onClick={handleCashe}
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
		</>
	);
}

export default AskPublic;
