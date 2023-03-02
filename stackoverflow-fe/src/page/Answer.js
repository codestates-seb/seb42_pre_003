import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';
import AnsEditor from '../components/answer/AnsEditor';
import useAnsStore from '../store/ansStore';
import RightMenu from '../components/list/RightMenu';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import AnsEdit from '../components/answer/AnsEdit';

const BREAK_POINT_PC = 1100;

const AnsWrap = styled.div`
	max-width: 830px;
	width: 100%;
	padding: 3rem 1.33rem;
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

const SideWrap = styled.div`
	margin-top: 3rem;
	@media only screen and (max-width: ${BREAK_POINT_PC}px) {
		display: none;
	}
`;

function Answer() {
	let { id } = useParams();
	const { page, answer, answerBind, answerReset } = useAnsStore();
	const { ansItem, ansDownList } = useAnsStore();
	const { getAnswerItem, getAnsDown, addDown } = useAnsStore();

	useEffect(() => {
		getAnswerItem(`${process.env.REACT_APP_API_URL}/questions/${id}`);
	}, [getAnswerItem, id]);

	const handleAnswer = (e) => {
		e.preventDefault();

		const item = {
			questionId: id,
			answerContent: answer,
		};

		addDown(`${process.env.REACT_APP_API_URL}/answers`, item);
		answerReset(ansDownList);
		setTimeout(() => {
			window.location.reload();
		}, 300);
	};

	useEffect(() => {
		getAnsDown(`${process.env.REACT_APP_API_URL}/answers?questionId=${id}`);
	}, [getAnsDown, id]);

	return (
		<>
			{ansItem.data && (
				<>
					<AnsWrap>
						{page === 'read' ? (
							<>
								<AnsHeader data={ansItem.data} />
								<AnsCon type={'question'} data={ansItem.data} />
								<ConTitle>{`${ansItem.data.answers || 0} Answers`}</ConTitle>
								{ansDownList.data &&
									ansDownList.data.map((el, idx) => (
										<AnsCon key={ansDownList.data.answerId || idx} data={el} />
									))}
								<>
									<ConTitle>Your Answer</ConTitle>
									<AnsEditor value={answer} func={answerBind} />
									<InputButton onClick={handleAnswer}>
										post your answer
									</InputButton>
								</>
							</>
						) : (
							<>
								<AnsEdit />
							</>
						)}
					</AnsWrap>
					<SideWrap>
						<RightMenu />
					</SideWrap>
				</>
			)}
		</>
	);
}

export default Answer;
