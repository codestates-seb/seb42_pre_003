import styled from 'styled-components';
import { useState } from 'react';
import { SelectIcon } from './AskIcon';

const SelectBox = styled.div`
	border: 1px solid hsl(210, 8%, 75%);
	border-radius: 0.188rem;
	h5 {
		display: flex;
		padding: 0.65rem 0.7rem;
		justify-content: space-between;
		cursor: pointer;
		strong {
			font-size: 0.75rem;
			font-weight: 600;
			color: hsl(210, 8%, 45%);
		}
	}

	ul {
		border-top: 1px solid #e1e0e0;
	}
	li {
		padding: 0.65rem 0.5rem;
		font-size: 0.75rem;
		text-align: center;
	}
`;

function AskSelector() {
	const [active, setActive] = useState(false);
	const handleSelect = () => {
		setActive(!active);
	};

	return (
		<SelectBox>
			<h5 onClick={handleSelect}>
				<strong>Do any of these posts answer your question?</strong>
				<SelectIcon />
			</h5>
			{active ? (
				<ul>
					<li>No duplicate questions found.</li>
				</ul>
			) : null}
		</SelectBox>
	);
}
export default AskSelector;
