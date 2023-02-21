import styled from 'styled-components';
import AskEditor from './AskEditor';

const InputBox = styled.div`
	margin-top: 0.65rem;
	padding: 1.5rem;
	border: 1px solid #e3e6e8;
	border-radius: 0.188rem;
	h4 {
		font-size: 0.85rem;
		font-weight: 700;
	}
	span {
		font-size: 0.7rem;
	}
`;

const Input = styled.input`
	display: block;
	width: 100%;
	height: 1.5rem;
	margin-top: 0.35rem;
	padding: 0.6em 0.7em;
	font-size: 0.6rem;
	border: 1px solid #babfc4;
	border-radius: 0.188rem;
	&::placeholder {
		font-weight: 500;
		color: rgb(59 64 69 / 39%);
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
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4); ;
`;

function AskInput({ type, label, text, values }) {
	return (
		<InputBox>
			<h4>{label}</h4>
			<span>{text}</span>
			{type === 'editor' ? (
				<AskEditor />
			) : (
				<Input
					{...values}
					placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
				/>
			)}

			<InputButton>Next</InputButton>
		</InputBox>
	);
}

export default AskInput;
