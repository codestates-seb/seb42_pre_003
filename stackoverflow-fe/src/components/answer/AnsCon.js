import styled from 'styled-components';
import AnsSide from './AnsSide';
import AnsComment from './AnsComment';
import AnsInfo from './AnsInfo';

const BREAK_POINT_MO = 576;

const ConWrap = styled.div`
	display: flex;
	margin-top: 1rem;
	padding-bottom: 1rem;
	border-bottom: ${(props) =>
		props.type === 'question' ? null : '1px solid #e3e6e8'};
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
		@media only screen and (max-width: ${BREAK_POINT_MO}px) {
			flex-wrap: wrap;
		}
		li {
			padding: 0.25rem;
			border-radius: 0.188rem;
			background: rgba(0, 0, 0, 0.5);
			color: #fff;
		}
	}
`;

function AnsCon({ type, handleComment }) {
	return (
		<ConWrap type={type}>
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
				{type === 'question' ? (
					<AnsTag>
						<ul>
							<li>javascript</li>
							<li>node.js</li>
							<li>mongodb</li>
							<li>express</li>
							<li>mongoose</li>
						</ul>
					</AnsTag>
				) : null}
				<AnsInfo />
				<AnsComment handleComment={handleComment} />
			</AnsBox>
		</ConWrap>
	);
}

export default AnsCon;
