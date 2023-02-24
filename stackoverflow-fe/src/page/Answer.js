import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';
import AnsEditor from '../components/answer/AnsEditor';
import AnsInput from '../components/answer/AnsInput';
import useAnsStore from '../store/ansStore';
import RightMenu from '../components/list/RightMenu';

/*sample*/
// import Paging from '../components/pagination/Paging';

const AnsWrap = styled.div`
	max-width: 830px;
	width: 100%;
	padding: 3rem 1.66rem;
`;

const ConTitle = styled.h4`
	margin: 1rem 0 0.75rem;
	font-weight: 500;
`;

const InputButton = styled.button`
	margin-top: 0.8rem;
	padding: 0.45rem 0.5rem;
	color: #fff;
	font-size: 0.6rem;
	font-weight: 500;
	border-radius: 0.188rem;
	background: hsl(206, 100%, 52%);
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4);
	cursor: ${(props) => (props.disabled ? 'not-allowed' : 'cursor')};
`;

function Answer() {
	const {
		page,
		answer,
		answerBind,
		answerReset,
		edTitle,
		edBody,
		edTag,
		edTitleBind,
		edBodyBind,
		edTagBind,
	} = useAnsStore();

	const handleAnswer = (e) => {
		e.preventDefault();

		console.log(answer);
		answerReset();
	};

	const handleEdit = (e) => {
		e.preventDefault();

		const item = {
			title: edTitle,
			body: edBody,
			tag: edTag,
		};

		console.log(item);
	};

	// const { fishies, fetch } = useAnsStore();

	// console.log(fishies);

	return (
		<>
			<AnsWrap>
				{page === 'read' ? (
					<>
						{/* <button onClick={fetch}>call</button> */}
						<AnsHeader />
						<AnsCon type={'question'} />
						<ConTitle>2 Answers</ConTitle>
						{[0, 1].map((el, idx) => (
							<AnsCon key={idx} />
						))}
						<>
							<ConTitle>Your Answer</ConTitle>
							<AnsEditor func={answerBind} />
							<InputButton onClick={handleAnswer}>post your answer</InputButton>
						</>
					</>
				) : (
					<>
						<ConTitle>Title</ConTitle>
						<AnsInput func={edTitleBind} />
						<ConTitle>Body</ConTitle>
						<AnsEditor func={edBodyBind} />
						<ConTitle>Tags</ConTitle>
						<AnsInput func={edTagBind} />
						<InputButton onClick={handleEdit}>Save Edits</InputButton>
					</>
				)}

				{/* sample */}
				{/* <Paging /> */}
			</AnsWrap>
			<div style={{ marginTop: '3rem' }}>
				<RightMenu />
			</div>
		</>
	);
}

export default Answer;
