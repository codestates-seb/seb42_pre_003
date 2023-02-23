import AskNotice from './AskNotice';
import AskBox from './AskBox';

function AskReview({ titleBind, bodyBind, tagBind }) {
	return (
		<>
			<h3>Review your question</h3>
			<AskNotice review />
			<AskBox
				review
				name={'title'}
				values={titleBind}
				inputLabel={'Title'}
				inputText={
					'Be specific and imagine you’re asking a question to another person.'
				}
				guideTitle={'Writing a good title'}
				guideDes={`Your title should summarize the problem.\n
				You might find that you have a better idea of your title after writing out the rest of the question.`}
			/>
			<AskBox
				review
				name={'body'}
				type={'editor'}
				values={bodyBind}
				inputLabel={'Body'}
				inputText={
					'The body of your question contains your problem details and results. Minimum 30 characters.'
				}
				guideTitle={'Proof-read before posting'}
				guideDes={`Now that you’re ready to post your question, read through it from start to finish. Does it make sense?\n
        Add any details you missed and read through it again. Now is a good time to make sure that your title still describes the problem!`}
			/>
			<AskBox
				review
				name={'tag'}
				values={tagBind}
				inputLabel={'Tags'}
				inputText={
					'Add up to 5 tags to describe what your question is about. Start typing to see suggestions.'
				}
				guideTitle={'Adding tags'}
				guideDes={`Tags help ensure that your question will get attention from the right people.\n
				Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.\n
				Learn more about tagging`}
			/>
		</>
	);
}

export default AskReview;
