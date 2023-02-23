import styled from 'styled-components';
import AskEditor from '../ask/AskEditor';

const ConTitle = styled.h4`
	margin-top: 1rem;
	margin-bottom: 1rem;
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

function AnsAdd({ answerBind, handleAnswer }) {
	return (
		<>
			<ConTitle>Your Answer</ConTitle>
			<AskEditor able={'false'} values={answerBind} />
			<InputButton onClick={handleAnswer}>post your answer</InputButton>
		</>
	);
}

export default AnsAdd;
