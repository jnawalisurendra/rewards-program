package com.demoproject.rewardsprogram.util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RewardsUtilTest {
    @Test
    public void testRewardsPoint() {
        Assert.assertEquals(90, RewardsUtil.getRewardPoints(120));
        Assert.assertEquals(50, RewardsUtil.getRewardPoints(100));
    }
}