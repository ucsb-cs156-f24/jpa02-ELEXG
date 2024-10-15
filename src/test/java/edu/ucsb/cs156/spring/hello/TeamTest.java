package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

    Team team;

    @BeforeEach
    public void setup() {
        team = new Team("test-team");
        team.addMember("member1");    
    }

    @Test
    public void getName_returns_correct_name() {
       assert(team.getName().equals("test-team"));
    }

    @Test
    public void toString_returns_correct_string() {
        assertEquals("Team(name=test-team, members=[member1])", team.toString());
    }

    @Test
    public void equals_returns_correct_boolean() {
        // case 1: same object
        assert(team.equals(team));

        // case 2: different class
        assert(!team.equals(5));   
        
        // case 3: TT
        Team sameNameAndSameMembersTeam = new Team("test-team");
        sameNameAndSameMembersTeam.addMember("member1");
        assert(team.equals(sameNameAndSameMembersTeam));

        // case 3: TF
        Team sameNameAndDifferentMembersTeam = new Team("test-team");
        sameNameAndDifferentMembersTeam.addMember("member2");
        assert(!team.equals(sameNameAndDifferentMembersTeam));

        // Note for case 3: we do not need to cover FT nor FF due to 
        // short-circuit boolean evaluation
    }

    @Test 
    public void hashCode_returns_correct_hash() {
        Team t1 = new Team();
        t1.setName("foo");
        t1.addMember("bar");
        Team t2 = new Team();
        t2.setName("foo");
        t2.addMember("bar");
        assertEquals(t1.hashCode(), t2.hashCode());

        // covering mutatation when bitwise OR is replaced with bitwise AND 
        // which is another correct implementation
        Team t = new Team();
        t.setName("fasd");
        int result = t.hashCode();
        int expectedResult = 3135565;  // hashcode
        assertEquals(expectedResult, result);
    }
}
