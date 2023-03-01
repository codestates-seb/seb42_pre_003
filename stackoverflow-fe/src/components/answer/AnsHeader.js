import styled from 'styled-components';
import AskButton from '../list/AskButton';

const BREAK_POINT_MO = 576;

const HeaderWrap = styled.div`
	padding-bottom: 1rem;
	border-bottom: 1px solid #e3e6e8;
	h3 {
		display: flex;
		margin-bottom: 0.8rem;
		align-items: center;
		justify-content: space-between;
		font-size: 1.2rem;
		font-weight: 600;
		color: #3b4045;
		@media only screen and (max-width: ${BREAK_POINT_MO}px) {
			flex-direction: column;
			section {
				margin-top: 0.6rem;
			}
		}
	}
`;

const HeaderInfo = styled.ul`
	display: flex;
	gap: 0.4rem;
	font-size: 0.65rem;
	@media only screen and (max-width: ${BREAK_POINT_MO}px) {
		flex-direction: column;
	}
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

function AnsHeader({ data }) {
	return (
		<>
			{data && (
				<HeaderWrap>
					<h3>
						{data.questionTitle || 'How to handle mongoose errors globaly'}
						<AskButton />
					</h3>

					<HeaderInfo>
						<li>
							<strong>Asked</strong>
							<span>{data.createdAt || '9 days ago'}</span>
						</li>
						<li>
							<strong>Modified</strong>
							<span>{data.modifiedAt || 'today'}</span>
						</li>
						<li>
							<strong>Viewed</strong>
							<span>{`${data.views || '141'} times`}</span>
						</li>
					</HeaderInfo>
				</HeaderWrap>
			)}
		</>
	);
}

export default AnsHeader;
