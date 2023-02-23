import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';
import { useAskInput, useAskEditor } from '../util/askUtil/useAskInput';
import AnsList from '../components/answer/AnsList';
import AnsAdd from '../components/answer/AnsAdd';
import AnsEdit from '../components/answer/AnsEdit';
import { useState } from 'react';

/*sample*/
import Paging from '../components/pagination/Paging';

const AnsWrap = styled.div`
	max-width: 728px;
	margin: 0 auto;
	padding: 3rem 0.66rem;
`;

function Answer() {
	const [answerValue, answerBind, answerReset] = useAskEditor('');
	const [comValue, comBind, comReset] = useAskInput('');
	const [vote, setVote] = useState(0);
	const [book, setBook] = useState(false);
	const [page, setPage] = useState('read');

	const handleAnswer = (e) => {
		e.preventDefault();

		const answer = {
			answer: answerValue,
		};

		//api - patch
		console.log(answer);
		answerReset();
	};

	const handleComment = (e) => {
		e.preventDefault();

		const comment = {
			comment: comValue,
		};

		//api - patch
		console.log(comment);
		comReset();
	};

	const plusVote = () => {
		setVote(vote + 1);
	};

	const minusVote = () => {
		setVote(vote - 1);
	};

	const handleBook = () => {
		setBook(!book);
	};

	const handlePage = () => {
		setPage('edit');
	};

	return (
		<AnsWrap>
			{page === 'read' ? (
				<>
					<AnsHeader />
					<AnsCon
						type={'question'}
						comBind={comBind}
						handleComment={handleComment}
						vote={vote}
						plusVote={plusVote}
						minusVote={minusVote}
						book={book}
						handleBook={handleBook}
						handlePage={handlePage}
					/>
					<AnsList
						comBind={comBind}
						handleComment={handleComment}
						vote={vote}
						plusVote={plusVote}
						minusVote={minusVote}
						book={book}
						handleBook={handleBook}
						handlePage={handlePage}
					/>
					<AnsAdd answerBind={answerBind} handleAnswer={handleAnswer} />{' '}
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
