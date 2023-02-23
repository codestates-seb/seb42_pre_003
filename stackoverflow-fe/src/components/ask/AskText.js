import styled from 'styled-components';
import AskEditor from './AskEditor';
import AskSelector from './AskSelector';
import AskInput from './AskInput';

const BREAK_POINT_PC = 1100;

const InputBox = styled.div`
	width: 70%;
	padding: 1.5rem;
	border: 1px solid #e3e6e8;
	border-radius: 0.188rem;
	cursor: ${(props) => (props.able ? 'initial' : 'not-allowed')};
	h4 {
		font-size: 0.85rem;
		font-weight: 700;
	}
	.inputText {
		display: block;
		margin: 0.35rem 0;
		font-size: 0.7rem;
	}
	@media only screen and (max-width: ${BREAK_POINT_PC}px) {
		width: 100%;
	}
`;

const InputButton = styled.button`
	margin-top: 0.4rem;
	padding: 0.45rem 0.5rem;
	color: #fff;
	font-size: 0.6rem;
	font-weight: 500;
	border-radius: 0.188rem;
	background: hsl(206, 100%, 52%);
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4);
	cursor: ${(props) => (props.disabled ? 'not-allowed' : 'cursor')};
`;

function AskText({ able, type, label, text, values, review, ...rest }) {
	const textBoxHandler = (type) => {
		switch (type) {
			case 'editor':
				return <AskEditor able={able} func={values} />;
			case 'selector':
				return <AskSelector able={able} />;
			default:
				return <AskInput able={able} func={values} />;
		}
	};

	return (
		<InputBox able={able}>
			<h4>{label}</h4>
			<span className='inputText'>{text}</span>
			{textBoxHandler(type)}
			{!review ? (
				<InputButton type='button' disabled={!able} {...rest}>
					{type === 'selector' ? 'Review your question' : 'Next'}
				</InputButton>
			) : null}
		</InputBox>
	);
}

export default AskText;
