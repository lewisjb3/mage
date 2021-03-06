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
package mage.sets.fatereforged;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.Filter;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author LevelX2
 */
public class AleshaWhoSmilesAtDeath extends CardImpl {

    private static final FilterCreatureCard filter = new FilterCreatureCard("creature card with power 2 or less");

    static {
        filter.add(new PowerPredicate(Filter.ComparisonType.LessThan, 3));
    }

    public AleshaWhoSmilesAtDeath(UUID ownerId) {
        super(ownerId, 90, "Alesha, Who Smiles at Death", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{2}{R}");
        this.expansionSetCode = "FRF";
        this.supertype.add("Legendary");
        this.subtype.add("Human");
        this.subtype.add("Warrior");
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());
        
        // Whenever Alesha, Who Smiles at Death attacks, you may pay {W/B}{W/B}. If you do, return target creature card with power 2 or less from your graveyard to the battlefield tapped and attacking.
        Ability ability = new AttacksTriggeredAbility(new DoIfCostPaid(new AleshaWhoSmilesAtDeathEffect(), new ManaCostsImpl("{W/B}{W/B}")), false);
        ability.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability);        
    }

    public AleshaWhoSmilesAtDeath(final AleshaWhoSmilesAtDeath card) {
        super(card);
    }

    @Override
    public AleshaWhoSmilesAtDeath copy() {
        return new AleshaWhoSmilesAtDeath(this);
    }
}

class AleshaWhoSmilesAtDeathEffect extends OneShotEffect {

    public AleshaWhoSmilesAtDeathEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "return target creature card with power 2 or less from your graveyard to the battlefield tapped and attacking";
    }

    public AleshaWhoSmilesAtDeathEffect(final AleshaWhoSmilesAtDeathEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());

        if (controller != null) {
            Card card = game.getCard(getTargetPointer().getFirst(game, source));
            if (card != null) {
                if (card.putOntoBattlefield(game, Zone.GRAVEYARD, source.getSourceId(), source.getControllerId(), true)) {
                    Permanent permanent = game.getPermanent(card.getId());
                    game.getCombat().addAttackingCreature(permanent.getId(), game);
                }
            }
            return true;

        }
        return false;
    }

    @Override
    public AleshaWhoSmilesAtDeathEffect copy() {
        return new AleshaWhoSmilesAtDeathEffect(this);
    }

}
