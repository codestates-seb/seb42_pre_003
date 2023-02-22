import styled from 'styled-components';
import AnsHeader from '../components/answer/AnsHeader';
import AnsCon from '../components/answer/AnsCon';

const AnsWrap = styled.div`
	max-width: 728px;
	margin: 3rem auto;
`;

// const AnsWrite = styled.div`
// 	border: 1px solid blue;
// `;

function Answer() {
	return (
		<AnsWrap>
			<AnsHeader />
			<AnsCon />
			{/* <AnsWrite>
				<h4>Your Answer</h4>
			</AnsWrite> */}
		</AnsWrap>
	);
}

export default Answer;
