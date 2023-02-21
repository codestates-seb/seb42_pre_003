import styled from 'styled-components';
import { useState } from 'react';

const SelectBox = styled.div`
	border: 1px solid hsl(210, 8%, 75%);
	border-radius: 0.188rem;
	strong {
		display: block;
		padding: 0.65rem 0.7rem;
		font-size: 0.75rem;
		font-weight: 600;
		color: hsl(210, 8%, 45%);
		cursor: pointer;
	}
	ul {
		border-top: 1px solid #e1e0e0;
	}
	li {
		padding: 0.65rem 0.5rem;
		font-size: 0.75rem;
		text-align: ${(props) => (props.none ? 'center' : 'left')};
	}
`;

function AskSelector() {
	const [active, setActive] = useState(false);
	const handleSelect = () => {
		setActive(!active);
	};

	return (
		<SelectBox>
			<strong onClick={handleSelect}>
				Do any of these posts answer your question?
			</strong>
			{active ? (
				<ul>
					<li none={'false'}>No duplicate questions found.</li>
				</ul>
			) : null}
		</SelectBox>
	);
}
export default AskSelector;
