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
package mage.sets.tenth;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.BecomesCreatureSourceEffect;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.game.permanent.token.Token;

/**
 *
 * @author Loki
 */
public class ForbiddingWatchtower extends CardImpl {

    public ForbiddingWatchtower(UUID ownerId) {
        super(ownerId, 352, "Forbidding Watchtower", Rarity.UNCOMMON, new CardType[]{CardType.LAND}, "");
        this.expansionSetCode = "10E";

        // Forbidding Watchtower enters the battlefield tapped.
        this.addAbility(new EntersBattlefieldTappedAbility());

        // {T}: Add {W} to your mana pool.
        this.addAbility(new WhiteManaAbility());

        // {1}{W}: Forbidding Watchtower becomes a 1/5 white Soldier creature until end of turn. It's still a land.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new BecomesCreatureSourceEffect(new ForbiddingWatchtowerToken(), "land", Duration.EndOfTurn), new ManaCostsImpl("{1}{W}")));
    }

    public ForbiddingWatchtower(final ForbiddingWatchtower card) {
        super(card);
    }

    @Override
    public ForbiddingWatchtower copy() {
        return new ForbiddingWatchtower(this);
    }
}

class ForbiddingWatchtowerToken extends Token {
    ForbiddingWatchtowerToken() {
        super("Soldier", "a 1/5 white Soldier creature");
        cardType.add(CardType.CREATURE);
        this.subtype.add("Soldier");
        color.setWhite(true);
        
        power = new MageInt(1);
        toughness = new MageInt(5);
    }
}
