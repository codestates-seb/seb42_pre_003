import AnsInput from './AnsInput';
import AnsEditor from './AnsEditor';
import styled from 'styled-components';
import useAnsStore from '../../store/ansStore';

const InputBtnBox = styled.div`
	display: flex;
	gap: 0.35rem;
	align-items: center;
	button {
		margin-top: 0.8rem;
		padding: 0.45rem 0.5rem;
		font-size: 0.65rem;
		border-radius: 0.188rem;
		border: 1px solid #d7d8d9;
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
	cursor: ${(props) => (props.disabled ? 'not-allowed' : 'cursor')};
`;

const ConTitle = styled.h4`
	margin: 1rem 0 0.75rem;
	font-weight: 500;
`;

function AnsEdit({ data }) {
	console.log(data);

	const { edTitleBind, edBodyBind, edTagBind } = useAnsStore();
	const { edTitle, edBody, edTag } = useAnsStore();

	const handleEdit = (e) => {
		e.preventDefault();

		const item = {
			title: edTitle,
			body: edBody,
			tag: edTag,
		};

		console.log(item);
	};

	const handleDel = (e) => {
		e.preventDefault();

		console.log('check');
	};

	return (
		<>
			<ConTitle>Title</ConTitle>
			<AnsInput value={edTitle} func={edTitleBind} />
			<ConTitle>Body</ConTitle>
			<AnsEditor value={edBody} func={edBodyBind} />
			<ConTitle>Tags</ConTitle>
			<AnsInput func={edTagBind} />
			<InputBtnBox>
				<InputButton onClick={handleEdit}>Save Edits</InputButton>
				<button onClick={handleDel}>Delete</button>
				<button>close</button>
			</InputBtnBox>
		</>
	);
}

export default AnsEdit;
