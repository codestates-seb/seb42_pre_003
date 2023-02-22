import styled from 'styled-components';
import AnsCon from './AnsCon';

const ConTitle = styled.h4`
	margin-top: 1rem;
	font-weight: 500;
`;

function AnsList({
	comBind,
	handleComment,
	vote,
	plusVote,
	minusVote,
	book,
	handleBook,
	handlePage,
}) {
	return (
		<>
			<ConTitle>2 Answers</ConTitle>
			{[0, 1].map((el, idx) => (
				<AnsCon
					key={idx}
					comBind={comBind}
					handleComment={handleComment}
					vote={vote}
					plusVote={plusVote}
					minusVote={minusVote}
					handleBook={handleBook}
					book={book}
					handlePage={handlePage}
				/>
			))}
		</>
	);
}

export default AnsList;
