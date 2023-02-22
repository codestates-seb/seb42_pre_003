import styled from 'styled-components';
import AnsSide from './AnsSide';
import AnsComment from './AnsComment';
import AnsInfo from './AnsInfo';

const ConWrap = styled.div`
	display: flex;
`;

const AnsBox = styled.div`
	p {
		padding-bottom: 1rem;
		font-size: 0.75rem;
		font-weight: 500;
		line-height: 1.5;
		white-space: pre-line;
	}
`;

const AnsTag = styled.div`
	margin-bottom: 2rem;
	ul {
		display: flex;
		gap: 0.4rem;
		font-size: 0.55rem;
		font-weight: 500;
		li {
			padding: 0.25rem;
			border-radius: 0.188rem;
			background: rgba(0, 0, 0, 0.5);
			color: #fff;
		}
	}
`;

function AnsCon() {
	return (
		<ConWrap>
			<AnsSide />
			<AnsBox>
				<p>
					As for now, I'm using mongoose middleware to handle Mongoose specific
					errors (validation, cast, ....). I'm using the following code in all
					of my schemas: As for now, I'm using mongoose middleware to handle
					Mongoose specific errors (validation, cast, ....). I'm using the
					following code in all of my schemas: As for now, I'm using mongoose
					middleware to handle Mongoose specific errors (validation, cast,
					....). I'm using the following code in all of my schemas: As for now,
					I'm using mongoose middleware to handle Mongoose specific errors
					(validation, cast, ....). I'm using the following code in all of my
					schemas: As for now, I'm using mongoose middleware to handle Mongoose
					specific errors (validation, cast, ....). I'm using the following code
					in all of my schemas: As for now, I'm using mongoose middleware to
					handle Mongoose specific errors (validation, cast, ....). I'm using
					the following code in all of my schemas: As for now, I'm using
					mongoose middleware to handle Mongoose specific errors (validation,
					cast, ....). I'm using the following code in all of my schemas:
				</p>
				<AnsTag>
					<ul>
						<li>javascript</li>
						<li>node.js</li>
						<li>mongodb</li>
						<li>express</li>
						<li>mongoose</li>
					</ul>
				</AnsTag>
				<AnsInfo />
				<AnsComment />
			</AnsBox>
		</ConWrap>
	);
}

export default AnsCon;
