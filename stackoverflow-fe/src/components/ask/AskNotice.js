import styled from 'styled-components';

const NoticeBox = styled.div`
	margin-top: 0.65rem;
	padding: 1.5rem;
	border: 1px solid #a6ceed;
	border-radius: 0.188rem;
	h4 {
		font-size: 1.1rem;
		font-weight: 600;
		color: hsl(210, 8%, 25%);
	}
	p {
		margin: 0.5rem 0;
		font-size: 0.8rem;
		font-weight: 500;
		line-height: 1.2;
	}
	strong {
		display: block;
		margin: 0.8rem 0 0.4rem;
		font-size: 0.7rem;
		font-weight: 600;
	}
	ul {
		margin-left: 1rem;
	}
	li {
		position: relative;
		padding-left: 0.6rem;
		font-size: 0.7rem;
		font-weight: 500;
		line-height: 1.2;
		&::before {
			display: block;
			position: absolute;
			content: '';
			width: 0.2rem;
			height: 0.2rem;
			top: 0.25rem;
			left: 0;
			background: #000;
			border-radius: 100%;
		}
	}
`;

function AskNotice({ review }) {
	return (
		<NoticeBox>
			{review ? (
				<p>Please do a final review of your question and then post.</p>
			) : (
				<>
					<h4>Writing a good question</h4>
					<p>
						You’re ready to ask a programming-related question and this form
						will help guide you through the process.
						<br />
						Looking to ask a non-programming question? See the topics here to
						find a relevant site.
					</p>
					<strong>Steps</strong>
					<ul>
						<li>Summarize your problem in a one-line title.</li>
						<li>Describe your problem in more detail.</li>
						<li>Describe what you tried and what you expected to happen.</li>
						<li>
							Add “tags” which help surface your question to members of the
							community.
						</li>
						<li>Review your question and post it to the site.</li>
					</ul>
				</>
			)}
		</NoticeBox>
	);
}

export default AskNotice;
