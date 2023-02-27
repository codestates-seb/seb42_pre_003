import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';
import AnsEditor from '../components/answer/AnsEditor';
import AnsInput from '../components/answer/AnsInput';
import useAnsStore from '../store/ansStore';
import RightMenu from '../components/list/RightMenu';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';

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
		ansItem,
		getAnswerItem,
		ansDownList,
		getAnsDown,
		getCom,
		comList,
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

	let { id } = useParams();

	useEffect(() => {
		getAnswerItem(`${process.env.REACT_APP_API_URL}/questions/${id}`);
		getAnsDown(`${process.env.REACT_APP_API_URL}/answers?questionId=${id}`);
		getCom(
			`${process.env.REACT_APP_API_URL}/comments?qaType=Question&qaId=${id}`,
		);
	}, [getAnswerItem, getAnsDown, getCom, id]);

	// console.log(comList.data);

	return (
		<>
			{ansItem.data && (
				<>
					<AnsWrap>
						{page === 'read' ? (
							<>
								<AnsHeader data={ansItem.data} />
								<AnsCon
									type={'question'}
									data={ansItem.data}
									QaCom={comList.data}
								/>
								<ConTitle>{`${ansItem.data.answers} Answers`}</ConTitle>
								{ansDownList.data &&
									ansDownList.data.map((el, idx) => (
										<AnsCon key={ansDownList.data.answerId || idx} />
									))}
								<>
									<ConTitle>Your Answer</ConTitle>
									<AnsEditor func={answerBind} />
									<InputButton onClick={handleAnswer}>
										post your answer
									</InputButton>
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
					</AnsWrap>
					<div style={{ marginTop: '3rem' }}>
						<RightMenu />
					</div>
				</>
			)}
		</>
	);
}

export default Answer;
