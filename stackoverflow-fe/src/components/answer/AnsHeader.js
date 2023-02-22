import styled from 'styled-components';

const HeaderWrap = styled.div`
	margin-bottom: 1rem;
	padding-bottom: 1rem;
	border-bottom: 1px solid #e3e6e8;
	h3 {
		margin-bottom: 0.8rem;
		font-size: 1.2rem;
		font-weight: 600;
		color: #3b4045;
	}
`;

const HeaderInfo = styled.ul`
	display: flex;
	gap: 0.4rem;
	font-size: 0.65rem;
	li {
		display: flex;
		strong {
			font-weight: 500;
			color: #6a737c;
		}
		span {
			display: block;
			margin-left: 0.5rem;
		}
	}
`;

function AnsHeader() {
	return (
		<HeaderWrap>
			<h3>How to handle mongoose errors globaly</h3>
			<HeaderInfo>
				<li>
					<strong>Asked</strong>
					<span>9 days ago</span>
				</li>
				<li>
					<strong>Modified</strong>
					<span>today</span>
				</li>
				<li>
					<strong>Viewed</strong>
					<span>141 times</span>
				</li>
			</HeaderInfo>
		</HeaderWrap>
	);
}

export default AnsHeader;
