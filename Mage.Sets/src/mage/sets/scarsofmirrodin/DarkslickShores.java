/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

package mage.sets.scarsofmirrodin;

import java.util.UUID;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.condition.InvertCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.TapSourceEffect;
import mage.abilities.mana.BlackManaAbility;
import mage.abilities.mana.BlueManaAbility;
import mage.cards.CardImpl;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.permanent.AnotherPredicate;

/**
 *
 * @author maurer.it_at_gmail.com
 */
public class DarkslickShores extends CardImpl {

    private final static FilterLandPermanent filter = new FilterLandPermanent();
    
    static {
        filter.add(new AnotherPredicate());
    }

    public DarkslickShores (UUID ownerId) {
        super(ownerId, 226, "Darkslick Shores", Rarity.RARE, new CardType[]{CardType.LAND}, null);
        this.expansionSetCode = "SOM";

        // Darkslick Shores enters the battlefield tapped unless you control two or fewer other lands.
        Condition controls = new InvertCondition(new PermanentsOnTheBattlefieldCondition(filter, PermanentsOnTheBattlefieldCondition.CountType.FEWER_THAN, 3));
        String abilityText = "tapped unless you control two or fewer other lands";
        this.addAbility(new EntersBattlefieldAbility(new ConditionalOneShotEffect(new TapSourceEffect(), controls, abilityText), abilityText));
        
        this.addAbility(new BlueManaAbility());
        this.addAbility(new BlackManaAbility());
    }

    public DarkslickShores (final DarkslickShores card) {
        super(card);
    }

    @Override
    public DarkslickShores copy() {
        return new DarkslickShores(this);
    }

}
