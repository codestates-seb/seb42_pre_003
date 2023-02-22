import styled from 'styled-components';

const InfoWrap = styled.div`
	display: flex;
	padding-top: 0.3rem;
	align-items: flex-start;
	justify-content: space-between;
	color: hsl(206, 100%, 40%);
	font-weight: 600;
	font-size: 0.6rem;
`;

const InfoButton = styled.button`
	color: #6a737c;
	font-weight: 500;
	font-size: 0.65rem;
`;

const InfoUser = styled.div`
	margin-top: -0.3rem;
	padding: 0.3rem;
	color: #6a737c;
	font-weight: 600;
	font-size: 0.6rem;
	background: #d9e9f7;
	border-radius: 0.188rem;
`;

const InfoAva = styled.div`
	display: flex;
	margin-top: 0.3rem;
	align-items: center;
	img {
		max-width: 1.2rem;
		max-height: 1.2rem;
		border-radius: 0.133rem;
	}
	strong {
		display: block;
		padding: 0.33rem;
		color: hsl(206, 100%, 40%);
		font-weight: 600;
		font-size: 0.6rem;
	}
`;

function AnsInfo() {
	return (
		<InfoWrap>
			<InfoButton>Edit</InfoButton>
			<span>edited Feb 17 at 0:13</span>
			<InfoUser>
				<span>asked Feb 12 at 21:42</span>
				<InfoAva>
					<img
						src='https://lh3.googleusercontent.com/a/AATXAJx57pEL1MopWKG7KQUmxM6lwGKxtUd2EnXfPD0f=k-s64'
						alt='아바타'
					/>
					<strong>Ayoub k</strong>
				</InfoAva>
			</InfoUser>
		</InfoWrap>
	);
}

export default AnsInfo;
