import styled from 'styled-components';
import AskEditor from './AskEditor';
import AskSelector from './AskSelector';

const InputBox = styled.div`
	width: 70%;
	padding: 1.5rem;
	border: 1px solid #e3e6e8;
	border-radius: 0.188rem;
	h4 {
		font-size: 0.85rem;
		font-weight: 700;
	}
	span {
		display: block;
		margin: 0.35rem 0;
		font-size: 0.7rem;
	}
`;

const Input = styled.input`
	display: block;
	width: 100%;
	height: 1.5rem;
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
	const textBoxHandler = (type) => {
		switch (type) {
			case 'editor':
				return <AskEditor values={values} />;
			case 'selector':
				return <AskSelector />;
			default:
				return (
					<Input
						{...values}
						placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
					/>
				);
		}
	};

	return (
		<InputBox>
			<h4>{label}</h4>
			<span>{text}</span>
			{textBoxHandler(type)}
			<InputButton>
				{type === 'selector' ? 'Review your question' : 'Next'}
			</InputButton>
		</InputBox>
	);
}

export default AskInput;
