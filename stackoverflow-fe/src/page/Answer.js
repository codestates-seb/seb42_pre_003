import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';
import AnsList from '../components/answer/AnsList';
import AnsAdd from '../components/answer/AnsAdd';
import AnsEdit from '../components/answer/AnsEdit';
import useAnsStore from '../store/ansStore';

/*sample*/
import Paging from '../components/pagination/Paging';

const AnsWrap = styled.div`
	max-width: 728px;
	margin: 0 auto;
	padding: 3rem 0.66rem;
`;

function Answer() {
	const { page, answer, answerReset, comment, comReset } = useAnsStore();

	const handleAnswer = (e) => {
		e.preventDefault();

		//api - patch
		console.log(answer);
		answerReset();
	};

	const handleComment = (e) => {
		e.preventDefault();

		//api - patch
		console.log(comment);
		comReset();
	};

	return (
		<AnsWrap>
			{page === 'read' ? (
				<>
					<AnsHeader />
					<AnsCon type={'question'} handleComment={handleComment} />
					<AnsList handleComment={handleComment} />
					<AnsAdd handleAnswer={handleAnswer} />
				</>
			) : (
				<AnsEdit />
			)}

			{/* sample */}
			<Paging />
		</AnsWrap>
	);
}

export default Answer;
