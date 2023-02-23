import styled from 'styled-components';
import AskInput from '../ask/AskInput';
import AskEditor from '../ask/AskEditor';

const EditBox = styled.div`
	h4 {
		font-size: 0.85rem;
		font-weight: 700;
		margin: 1rem 0;
	}
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
`;

function AnsEdit() {
	return (
		<EditBox>
			<h4>Title</h4>
			<AskInput />
			<h4>Body</h4>
			<AskEditor />
			<h4>Tags</h4>
			<AskInput />
			<InputButton>Save Edits</InputButton>
		</EditBox>
	);
}

export default AnsEdit;
