import styled from 'styled-components';
import AnsCon from './AnsCon';

const ConTitle = styled.h4`
	margin-top: 1rem;
	font-weight: 500;
`;

function AnsList({ comBind, handleComment }) {
	return (
		<>
			<ConTitle>2 Answers</ConTitle>
			{[0, 1].map((el, idx) => (
				<AnsCon key={idx} comBind={comBind} handleComment={handleComment} />
			))}
		</>
	);
}

export default AnsList;
