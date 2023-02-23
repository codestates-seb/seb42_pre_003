import useAnsStore from '../../store/ansStore';
import styled from 'styled-components';

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

function AnsInput() {
	const { comBind } = useAnsStore();
	return <Input onChange={({ target: { value } }) => comBind(value)} />;
}
export default AnsInput;
