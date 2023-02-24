import styled from 'styled-components';

const Input = styled.input`
	display: block;
	width: 100%;
	height: 1.5rem;
	padding: 0.6em 0.7em;
	font-size: 0.6rem;
	border: 1px solid #babfc4;
	border-color: ${(props) => (props.value === undefined ? null : '#92c1e6')};
	box-shadow: ${(props) =>
		props.value === undefined ? null : '0 0 0 4px #dceaf7'};
	border-radius: 0.188rem;
	&::placeholder {
		font-weight: 500;
		color: rgb(59 64 69 / 39%);
	}
	&:focus {
		border: 1px solid #92c1e6;
		box-shadow: 0 0 0 4px #dceaf7;
	}
`;

function AskInput({ able, value, func }) {
	return (
		<Input
			disabled={!able}
			value={value}
			onChange={({ target: { value } }) => func(value)}
			placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
		/>
	);
}

export default AskInput;
