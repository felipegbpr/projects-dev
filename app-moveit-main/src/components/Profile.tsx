import { useContext } from 'react';
import { ChallengesContext } from '../contexts/ChallengesContext';

import styles from '../styles/components/Profile.module.css';


export function Profile() {
    const { level } = useContext(ChallengesContext);
    
    return (
        <div className={styles.profileContainer}>
            <img src="https://github.com/FelipeGB-pgr.png" alt="Felipe Borges" />
            <div>
                <strong>Felipe Borges</strong>
                <p>
                    <img src="icons/level.svg" alt="Level" />
                    Level {level}
                </p>
            </div>
        </div>
    );
}